package com.backend.jwt.dto.response.naver;

import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.entity.naver.Summary;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class SummaryResponseDto extends ResponseDto {

    private List<Summary> summaryList;

    private SummaryResponseDto(List<Summary> summaryList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.summaryList = summaryList;
    }


    // 200 success
    public static ResponseEntity<? super SummaryResponseDto> success(List<Summary> summaryList) {
        SummaryResponseDto result = new SummaryResponseDto(summaryList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 400 없는 flag
   public static ResponseEntity<ResponseDto> notFoundFlag(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_FLAG, ResponseMessage.NOT_EXISTED_FLAG);
        return ResponseEntity.status(HttpStatus.OK).body(result);
   }

    // 400 없는 type
    public static ResponseEntity<ResponseDto> notFoundType() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_TYPE, ResponseMessage.NOT_EXISTED_TYPE);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }


}
