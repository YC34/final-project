package com.backend.jwt.service.board;

import com.backend.jwt.dto.reqeust.BoardRequestDto;
import com.backend.jwt.dto.response.board.BoardResponseDto;
import com.backend.jwt.dto.response.board.GetBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    // TODO service 구현.
    ResponseEntity<? super BoardResponseDto> write(BoardRequestDto dto, String email);
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super BoardResponseDto> getBoards();
}
