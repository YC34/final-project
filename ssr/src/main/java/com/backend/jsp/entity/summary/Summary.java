package com.backend.jsp.entity.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

    /*
    * flag 1 : 총 수집한 count 하루전의 날까지 집계
    * flag 2 : 일별 총 수집한 count
    * flag 3 : 회사별 count
    * flag 4 : 회사,일별 count
    * */
    private Integer flag;

    /**
     * naver_news: naver news의 수집 내용
     */
    private String dataType;
    private String date;
    private String company;
    private Integer totalCount;
}
