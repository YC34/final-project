package com.backend.jwt.dto.response.naver;

import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.entity.naver.NaverNews;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class NewsListResponseDto extends ResponseDto {

    private Integer pageNum;
    private Integer totalPage;
    private List<NaverNews> naverNewsList;
    private NaverNews naverNews;

    // 단일 조회 생성자
    private NewsListResponseDto(NaverNews naverNews) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.naverNews = naverNews;
    }

    // 전체 조회 생성자
    private NewsListResponseDto(Integer pageNum, Integer totalPage, List<NaverNews> naverNewsList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.pageNum = pageNum;
        this.totalPage = totalPage;
        this.naverNewsList = naverNewsList;
    }

    // 200 전체 조회 성공
    public static ResponseEntity<? super NewsListResponseDto> success(Integer pageNum, Integer totalPage,List<NaverNews> naverNewsList) {
        NewsListResponseDto result = new NewsListResponseDto(pageNum,totalPage,naverNewsList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 200 단일 조회 성공
    public static ResponseEntity<NewsListResponseDto> success(NaverNews naverNews) {
        NewsListResponseDto result = new NewsListResponseDto(naverNews);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 400 없는 게시물
    public static ResponseEntity<ResponseDto> notFoundBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 403 권한 없음
    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }




}
