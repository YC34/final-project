package com.backend.jwt.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

/**
 * POSTMAN or front에서 요청 보낼떄 처음 테스트.
 *
 */


    @GetMapping
    public ResponseEntity<Map<String, String>> home() {
            Map<String, String> helloworldMap = new HashMap<>();
            helloworldMap.put("message", "Hello World");
            helloworldMap.put("status", "OK");
            helloworldMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
            helloworldMap.put("테스트용 key","value");

        return ResponseEntity.status(HttpStatus.OK).body(helloworldMap);
    }

}

