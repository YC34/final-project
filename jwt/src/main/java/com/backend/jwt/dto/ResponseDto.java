package com.backend.jwt.dto;


import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    // DB Error
    // Http Status - 500
    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto responseDto = new ResponseDto(ResponseCode.DATABASE_ERROR , ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    // 유효성검사 실패
    // Http Status - 400
    public static ResponseEntity<ResponseDto> validationFailed(){
        ResponseDto responseDto = new ResponseDto(ResponseCode.VALIDATE_FAILED , ResponseMessage.VALIDATE_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }


}
