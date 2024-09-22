package com.backend.jwt.service.naver.implement;

import com.backend.jwt.dao.naver.EconomicMapper;
import com.backend.jwt.dao.naver.NewsMapper;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.dto.reqeust.naver.EconomicRawDataRequestDto;
import com.backend.jwt.dto.reqeust.naver.NewsListRequestDto;
import com.backend.jwt.dto.response.naver.EconomicRawDataResponseDto;
import com.backend.jwt.dto.response.naver.NewsListResponseDto;
import com.backend.jwt.entity.naver.EconomicRawData;
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

    private final NewsMapper newsDao;
    private final EconomicMapper economicDao;

    @Autowired
    public NaverServiceImplement(NewsMapper newsDao ,EconomicMapper economicDao) {
        this.newsDao = newsDao;
        this.economicDao = economicDao;
    }

    @Override
    public ResponseEntity<? super NewsListResponseDto> getList(NewsListRequestDto dto) {
        Integer totalPages = null;
        Integer offset  = null;
        List<NaverNews> newsList = null;

        try{
            // TODO error Response
            Integer totalCount = newsDao.getTotalCount();
            log.info("Total count : {}", totalCount);

            totalPages = (totalCount + dto.getNumOfRows() -1) / dto.getNumOfRows();
            offset = (dto.getPageNo() - 1) * dto.getNumOfRows();
            newsList = newsDao.getList(dto.getNumOfRows() , offset);

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
             naverNews = newsDao.getDetail(naverNewsSequence);
             if(naverNews == null){
                 return NewsListResponseDto.notFoundBoard();
             }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return NewsListResponseDto.success(naverNews);
    }


    @Override
    public ResponseEntity<? super EconomicRawDataResponseDto> getData(EconomicRawDataRequestDto dto) {
        List<EconomicRawData> economicRawData = null;
        Integer offset  = null;
        try{
           // code check
           boolean existsCode = economicDao.existsCode(dto.getCode());
           if(!existsCode){
               return EconomicRawDataResponseDto.notFoundCode();
           }

           // type check
           boolean existsType = economicDao.existsType(dto.getEType());
           log.info("existsType : {}", existsType);
           if(!existsType){
               return EconomicRawDataResponseDto.notFoundType();
           }
           log.info("beginNum: {}",dto.getBeginNum());
           offset = (dto.getBeginNum() - 1) * dto.getNumOfRows();

           economicRawData = economicDao.getData(dto.getCode(),dto.getEType(),dto.getNumOfRows(),offset);
       }catch (Exception e){
           e.printStackTrace();
           return ResponseDto.databaseError();
       }
        return EconomicRawDataResponseDto.success(economicRawData);
    }
}
