package com.backend.jwt.dto.reqeust.naver;

import lombok.Getter;

import java.util.Map;

@Getter
public class SummaryRequestDto {

    private Integer flag;
    private String dataType;
    private String date;
    private String company;

    public SummaryRequestDto(Map<String, String> params) {
        // if문 있는 파라미터는 필수값이지만, default값이 존재한다.
        if (params.containsKey("flag") && !params.get("flag").isEmpty()) {
            this.flag = Integer.valueOf(params.get("flag"));
        } else {
            this.flag = 1; // 기본값
        }
        if (params.containsKey("dataType") && !params.get("dataType").isEmpty()) {
            this.dataType = params.get("dataType");
        } else {
            this.dataType = "naver_news"; // 기본값
        }


        this.date = params.get("date");
        this.company = params.get("company");

    }
}
