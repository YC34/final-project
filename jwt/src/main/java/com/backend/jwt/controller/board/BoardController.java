package com.backend.jwt.controller.board;


import com.backend.jwt.dto.reqeust.BoardRequestDto;
import com.backend.jwt.dto.response.board.BoardResponseDto;
import com.backend.jwt.dto.response.board.GetBoardResponseDto;
import com.backend.jwt.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Slf4j
public class BoardController {


    private final BoardService service;

    @PostMapping("/write")
    public ResponseEntity<? super BoardResponseDto> write(@RequestBody BoardRequestDto dto , @AuthenticationPrincipal String email) {
        ResponseEntity<? super BoardResponseDto> response = service.write(dto, email);
        return response;
    }

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(@PathVariable Integer boardNumber) {
        log.info("Get board number : {}", boardNumber);
        ResponseEntity<? super GetBoardResponseDto> response  = service.getBoard(boardNumber);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super BoardResponseDto> getBoardList() {
        ResponseEntity<? super BoardResponseDto> response = service.getBoards();
        return response;
    }

}
