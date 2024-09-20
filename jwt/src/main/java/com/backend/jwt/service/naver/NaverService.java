package com.backend.jwt.service.naver;


import com.backend.jwt.dto.reqeust.naver.EconomicRawDataRequestDto;
import com.backend.jwt.dto.reqeust.naver.NewsListRequestDto;
import com.backend.jwt.dto.response.naver.EconomicRawDataResponseDto;
import com.backend.jwt.dto.response.naver.NewsListResponseDto;
import org.springframework.http.ResponseEntity;

public interface NaverService {
    ResponseEntity<? super NewsListResponseDto> getList(NewsListRequestDto dto);
    ResponseEntity<? super NewsListResponseDto> getDetail(Integer naverNewsSequence);
    ResponseEntity<? super EconomicRawDataResponseDto> getData(EconomicRawDataRequestDto dto);
}
