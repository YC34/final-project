package com.backend.jwt.dto.response.auth;


import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {


    // 기본 생성자에 성공 stauts와 코드 , message
    private SignUpResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    // 200 ok
    public static ResponseEntity<SignUpResponseDto> success(){
        SignUpResponseDto result = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 400 bad request
    public static ResponseEntity<ResponseDto> duplicateEmail(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL,ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateUsername(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_USERNAME,ResponseMessage.DUPLICATE_USERNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    public static ResponseEntity<ResponseDto> duplicateTelNumber(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_TEL_NUMBER,ResponseMessage.DUPLICATE_TEL_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
