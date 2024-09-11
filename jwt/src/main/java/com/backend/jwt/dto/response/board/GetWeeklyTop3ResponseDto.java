package com.backend.jwt.dto.response.board;

import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class GetWeeklyTop3ResponseDto extends ResponseDto {

    private GetWeeklyTop3ResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<GetWeeklyTop3ResponseDto> success() {



        return null;
    }



}
