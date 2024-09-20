package com.backend.jwt.dto.reqeust.naver;

import lombok.Getter;
import java.util.Map;

@Getter
public class EconomicRawDataRequestDto {
    // 해당하는 지표에 대한 고유 code 필수값.
    private String code;
    // 어떤 지표인지에 대한 type 필수값.
    private String eType;

    // 한번에 가져갈 데이터 수
    private Integer numOfRows;
    // 전체 데이터에서 numOfORows 만큼를 기준으로 번호를 하였을 시 시작하는 번호.
    private Integer beginNum;

    public EconomicRawDataRequestDto(Map<String, String> params) {

        if (params.containsKey("beginNum") && !params.get("beginNum").isEmpty()) {
            this.beginNum = Integer.valueOf(params.get("beginNum"));
        } else {
            this.beginNum = 1; // 기본값
        }

        // numOfRows 기본값 설정
        if (params.containsKey("numOfRows") && !params.get("numOfRows").isEmpty()) {
            this.numOfRows = Integer.valueOf(params.get("numOfRows"));
        } else {
            this.numOfRows = 7; // 기본값
        }

        this.eType = params.get("eType");
        this.code = params.get("code");


    }
}
