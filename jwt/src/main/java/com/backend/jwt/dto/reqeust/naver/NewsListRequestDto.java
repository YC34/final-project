package com.backend.jwt.dto.reqeust.naver;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class NewsListRequestDto {

    // 모두다 선택 사항.
    // 없으면 기본 default pageNo =1 , numOfRows =10
    // 현재 페이지 번호
    private Integer pageNo;
    // 한페이지에 보여줄 수
    private Integer numOfRows;


    // 수집 시간 interval
    private String beginCreateAt;
    private String endCreateAt;

    // 뉴스가 올라온 시간 interval
    private String beginNewsDate;
    private String endNewsDate;

    // 뉴스 회사
    private String companyName;

    public NewsListRequestDto(Map<String, String > params) {


        if (params.containsKey("pageNo") && !params.get("pageNo").isEmpty()) {
            this.pageNo = Integer.valueOf(params.get("pageNo"));
        } else {
            this.pageNo = 1; // 기본값
        }

        // numOfRows 기본값 설정
        if (params.containsKey("numOfRows") && !params.get("numOfRows").isEmpty()) {
            this.numOfRows = Integer.valueOf(params.get("numOfRows"));
        } else {
            this.numOfRows = 10; // 기본값
        }

        this.beginCreateAt = params.get("beginCreateAt");
        this.endCreateAt = params.get("endCreateAt");
        this.beginNewsDate = params.get("beginNewsDate");
        this.endNewsDate = params.get("endNewsDate");
        this.companyName = params.get("companyName");
    }
}
