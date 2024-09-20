package com.backend.jwt.dto.response.naver;

import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.entity.naver.EconomicRawData;
import com.backend.jwt.entity.naver.NaverNews;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Getter
public class EconomicRawDataResponseDto extends ResponseDto {

    private List<EconomicRawData> economicRawData;


    // 기본 성공 생성자
    // 무조건 List 형태의 data를 보내준다. 기본 Default 는 7일
    private EconomicRawDataResponseDto(List<EconomicRawData> economicRawData) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.economicRawData = economicRawData;
    }

    // 200 조회 성공
    public static ResponseEntity<? super EconomicRawDataResponseDto> success(List<EconomicRawData> economicRawData) {
        EconomicRawDataResponseDto result = new EconomicRawDataResponseDto(economicRawData);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 400 없는 code
    public static ResponseEntity<ResponseDto> notFoundCode() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_CODE, ResponseMessage.NOT_EXISTED_CODE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 400 없는 type
    public static ResponseEntity<ResponseDto> notFoundType() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_TYPE, ResponseMessage.NOT_EXISTED_TYPE);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }


}
