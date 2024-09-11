package com.backend.jsp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    /*
       view 반환하는 컨트롤러
       action은 없고 화면만 반환하는 컨트롤러
     */

    @GetMapping("/")
    public String index(){
        return "home";
    }


}
