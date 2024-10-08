package com.backend.jwt.controller.naver;


import com.backend.jwt.dto.reqeust.naver.EconomicRawDataRequestDto;
import com.backend.jwt.dto.reqeust.naver.NewsListRequestDto;
import com.backend.jwt.dto.reqeust.naver.SummaryRequestDto;
import com.backend.jwt.dto.response.naver.EconomicRawDataResponseDto;
import com.backend.jwt.dto.response.naver.NewsListResponseDto;
import com.backend.jwt.dto.response.naver.SummaryResponseDto;
import com.backend.jwt.service.naver.NaverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/naver")
@Slf4j
public class NaverController {

    private final NaverService service;

    @Autowired
    public NaverController(NaverService service) {
        this.service = service;
    }

    // list
    @GetMapping
    public ResponseEntity<? super NewsListResponseDto> getList(@RequestParam Map<String,String> params){
        log.info("naver list request come? ");
        NewsListRequestDto dto = new NewsListRequestDto(params);
        ResponseEntity<? super  NewsListResponseDto> response = service.getList(dto);
        return response;
    }

    @GetMapping("/detail")
    public ResponseEntity<? super NewsListResponseDto> getDetail(@RequestParam(value = "naverNewsSequence") Integer naverNewsSequence){
        ResponseEntity<? super NewsListResponseDto> response = service.getDetail(naverNewsSequence);
        return response;
    }

    @GetMapping("/economic-data")
    public ResponseEntity<? super EconomicRawDataResponseDto> getEconomicData(@RequestParam Map<String,String> params){
        EconomicRawDataRequestDto dto = new EconomicRawDataRequestDto(params);
        ResponseEntity<? super EconomicRawDataResponseDto> response = service.getData(dto);
        return response;
    }

    @GetMapping("/summary")
    public ResponseEntity<? super SummaryResponseDto> getSummary(@RequestParam Map<String,String> params){
       SummaryRequestDto dto = new SummaryRequestDto(params);
       ResponseEntity<? super SummaryResponseDto> response = service.getSummaryList(dto);
       return response;
    }

}
