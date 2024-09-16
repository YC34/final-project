package com.backend.jsp.controller.naver;


import com.backend.jsp.dto.naver.CommentDto;
import com.backend.jsp.entity.naver.Comment;
import com.backend.jsp.service.naver.NaverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/naver")
@Slf4j
public class NaverController {

    private final NaverService service;
    @Autowired
    public NaverController(NaverService naverService) {
        this.service = naverService;
    }

    // list 반환 첫 화면 READ
    @GetMapping("/list")
    public String boardList(Model model
                           ,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        Map<String, List<?>> boardList = service.getBoardList(pageNum);
        model.addAttribute("pageNum", boardList.get("pageInfo").get(0));
        model.addAttribute("totalPage", boardList.get("pageInfo").get(1));
        model.addAttribute("naverNewsList", boardList.get("naverNewsList"));
        model.addAttribute("usdDataListJson", boardList.get("economicDataList").get(0));
        model.addAttribute("jpyDataListJson", boardList.get("economicDataList").get(1));
        model.addAttribute("btcDataListJson", boardList.get("economicDataList").get(2));
        return "naver/news-page";
    }

    @GetMapping("/detail")
    public String boardDetail(Model model, @RequestParam Integer nid){
        Map<String, Object> result = service.getNaverNews(nid);
        model.addAttribute("naverNews", result.get("naverNews"));

        model.addAttribute("commentList", (List<Comment>) result.get("commentList"));
        return "naver/news-detail-page";
    }


    @PostMapping("/comment/write")
    public String writeComment(CommentDto commentDto){
        log.info(commentDto.getContent());
        log.info(commentDto.getUserEmail());
        log.info(String.valueOf(commentDto.getBoardId()));
        log.info(String.valueOf(commentDto.getParentCommentId()));


        Integer count = service.writeComment(commentDto);

        return  "";
    }



}
