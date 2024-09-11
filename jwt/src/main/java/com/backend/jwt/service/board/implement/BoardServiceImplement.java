package com.backend.jwt.service.board.implement;

import com.backend.jwt.dao.board.BoardMapper;
import com.backend.jwt.dao.image.ImageMapper;
import com.backend.jwt.dao.auth.UserMapper;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.dto.object.BoardListItem;
import com.backend.jwt.dto.reqeust.BoardRequestDto;
import com.backend.jwt.dto.response.board.BoardResponseDto;
import com.backend.jwt.dto.response.board.GetBoardResponseDto;
import com.backend.jwt.entity.board.Board;
import com.backend.jwt.entity.image.Image;
import com.backend.jwt.service.board.BoardService;
import com.backend.jwt.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImplement implements BoardService {


    private final UserMapper userDao;
    private final BoardMapper boardDao;
    private final ImageMapper imageDao;
    private final FileService fileService;


    // write board
    @Override
    public ResponseEntity<? super BoardResponseDto> write(BoardRequestDto dto, String email) {

        try {
            // 유저 정보 확인.
            boolean existedEmail = userDao.existsByEmail(email);
            if(!existedEmail) return BoardResponseDto.notExistUser();

            // dto > entity
            Board board = new Board(dto,email);
            // insert board
            boardDao.write(board);
            Integer boardNumber = board.getBoardNumber();

            // image 경로를 가져와서 image table에 저장
            List<String> imageList = dto.getBoardImageList();
            List<Image> images = new ArrayList<>();

            for (String img : imageList) {
                Image image = new Image(boardNumber,img);
                images.add(image);
            }

            imageDao.saveAll(images);


        }catch (Exception e ){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return BoardResponseDto.success();
    }

    // get Board
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        BoardListItem boardItem = null;
        List<Image> imageList = new ArrayList<>();

        try {
          boardItem =  boardDao.getBoard(boardNumber);
          if( boardItem == null ) return GetBoardResponseDto.noExistsBoard();
          imageList = imageDao.findByBoardNumber(boardNumber);

          // viewCount +1; 한 항목에 대한 조회이니깐 viewCount +1
          Integer viewCount = boardDao.increaseViewCount(boardNumber);



        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(boardItem,imageList);
    }


    @Override
    public ResponseEntity<? super BoardResponseDto> getBoards() {
        return null;
    }
}

