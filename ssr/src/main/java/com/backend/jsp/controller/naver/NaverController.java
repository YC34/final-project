package com.backend.jsp.controller.naver;


import com.backend.jsp.dto.naver.CommentDto;
import com.backend.jsp.entity.naver.Comment;
import com.backend.jsp.service.naver.NaverService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<Comment> commets  = (List<Comment>) result.get("commentList");
        for (Comment comment : commets){
            log.info(comment.getUsername());
            log.info(comment.getUserEmail());
        }
        model.addAttribute("naverNews", result.get("naverNews"));
        model.addAttribute("commentList",  result.get("commentList"));
        model.addAttribute("replyList", result.get("replyList"));
        return "naver/news-detail-page";
    }


    @PostMapping("/comment/write")
    public String writeComment(CommentDto commentDto, RedirectAttributes redirectAttributes){
        Integer count = service.writeComment(commentDto);
        if(count > 0){
            log.info("nid {}" , commentDto.getBoardId());
            redirectAttributes.addAttribute("nid",commentDto.getBoardId());
            return "redirect:/naver/detail";
        }

        return  "redirect:/naver/list";
    }

//
    @PostMapping("/comment/delete")
    public String deleteComment(Integer commentUid, HttpSession session){
        log.info("nid {}" , commentUid);
        Integer naverNewsId = service.getNaverNewsId(commentUid);
        log.info(String.valueOf(naverNewsId));
        Integer result = service.deleteComment(commentUid);
        if(result < 0){
            log.info("nid {}" , naverNewsId);
            session.setAttribute("nid", naverNewsId);
            return "redirect:/naver/detail?nid=" + naverNewsId;
        }
        return "redirect:/naver/detail?nid=" + naverNewsId;
    }

}
