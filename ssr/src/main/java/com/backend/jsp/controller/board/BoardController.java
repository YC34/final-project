package com.backend.jsp.controller.board;


import com.backend.jsp.entity.board.NaverNews;
import com.backend.jsp.service.board.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService service;



    @Autowired
    public BoardController(BoardService boardService) {
        this.service = boardService;
    }

    // list 반환 첫 화면 READ
    @GetMapping("/list")
    public String boardList(Model model
                           ,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) throws JsonProcessingException {
        Map<String, List<?>> boardList = service.getBoardList(pageNum);
        model.addAttribute("pageNum", boardList.get("pageInfo").get(0));
        model.addAttribute("totalPage", boardList.get("pageInfo").get(1));
        model.addAttribute("naverNewsList", boardList.get("naverNewsList"));
        model.addAttribute("usdDataListJson", boardList.get("economicDataList").get(0));
        model.addAttribute("jpyDataListJson", boardList.get("economicDataList").get(1));
        model.addAttribute("btcDataListJson", boardList.get("economicDataList").get(2));
        return "board/news/board-news-page";
    }

    @GetMapping("/detail")
    public String boardDetail(Model model, @RequestParam Integer nid){
        log.info("nid {} ", nid);
        NaverNews naverNews = service.getNaverNews(nid);
        log.info("naverNews : {}", naverNews.getCompany());
        model.addAttribute("naverNews", naverNews);

        return "board/news/board-news-detail-page";
    }





    // goto page write CREATE
    @GetMapping("/write")
    public String boardWrite(){
        return "board/board-write-page";
    }

    // goto page update UPDATE
    @GetMapping("/update")
    public String boardUpdate(){
        return "board/board-update-page";
    }


    // action write CREATE
    @PostMapping("/write")
    public String boardWrite(NaverNews naverNews, Model model) {
        return "board-news-page";
    }


    // action update UPDATE
    @PostMapping("/update")
    public String boardUpdate(NaverNews naverNews, Model model) {
        return "board-news-page";
    }

    // action delete Delete
    @PostMapping("/delete")
    public String boardDelete(NaverNews naverNews) {
        return "board-news-page";
    }
}
