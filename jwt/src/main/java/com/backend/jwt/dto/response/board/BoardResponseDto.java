package com.backend.jwt.dto.response.board;


import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.entity.board.Board;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
public class BoardResponseDto extends ResponseDto {



    private BoardResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    // 200
    public static ResponseEntity<BoardResponseDto> success() {
        BoardResponseDto result = new BoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 400 없는 유저
    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }


    // 400 없는 게시물
    public static ResponseEntity<ResponseDto> notFoundBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 403 권한 없음
    public static ResponseEntity<ResponseDto> noPermission(Board board) {
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }



}
