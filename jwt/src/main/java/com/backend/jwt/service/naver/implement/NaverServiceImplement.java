package com.backend.jwt.service.naver.implement;

import com.backend.jwt.dao.naver.NaverMapper;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.dto.reqeust.naver.NewsListRequestDto;
import com.backend.jwt.dto.response.naver.NewsListResponseDto;
import com.backend.jwt.entity.naver.NaverNews;
import com.backend.jwt.service.naver.NaverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class NaverServiceImplement implements NaverService {

    // TODO 권한 문제로 exception 보내주기 만들기.

    private final NaverMapper dao;

    @Autowired
    public NaverServiceImplement(NaverMapper dao) {
        this.dao = dao;
    }

    @Override
    public ResponseEntity<? super NewsListResponseDto> getList(NewsListRequestDto dto) {
        Integer totalPages = null;
        Integer offset  = null;
        List<NaverNews> newsList = null;

        try{

            // TODO error Response
            Integer totalCount = dao.getTotalCount();
            log.info("Total count : {}", totalCount);

            // controller에서 어노테이션에서 막아 줄수 있지만, 혹시 모르니깐 set을 해준다.
            if(dto.getPageNo() == null){
                    dto.setPageNo(1);
               }

            if(dto.getNumOfRows() == null){
                    dto.setNumOfRows(10);
                }

            totalPages = (totalCount + dto.getNumOfRows() -1) / dto.getNumOfRows();
            offset = (dto.getPageNo() - 1) * dto.getNumOfRows();
            newsList = dao.getList(dto.getNumOfRows() , offset);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return NewsListResponseDto.success(dto.getPageNo(), totalPages, newsList);
    }


    @Override
    public ResponseEntity<? super NewsListResponseDto> getDetail(Integer naverNewsSequence) {
        NaverNews naverNews = null;
        try {
             naverNews = dao.getDetail(naverNewsSequence);
             if(naverNews == null){
                 return NewsListResponseDto.notFoundBoard();
             }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return NewsListResponseDto.success(naverNews);
    }
}
