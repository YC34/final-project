package com.backend.jwt.dto.response.board;

import com.backend.jwt.common.ResponseCode;
import com.backend.jwt.common.ResponseMessage;
import com.backend.jwt.dto.ResponseDto;
import com.backend.jwt.dto.object.BoardListItem;
import com.backend.jwt.entity.image.Image;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBoardResponseDto extends ResponseDto {
    private Integer boardNumber;
    private String title;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String writerEmail;
    private String writerProfileImage;


    private GetBoardResponseDto(BoardListItem boardItem, List<Image> imageList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for (Image image : imageList) {
            String boardImage = image.getImage();
            boardImageList.add(boardImage);
        }
        this.boardNumber = boardItem.getBoardNumber();
        this.title = boardItem.getTitle();
        this.content = boardItem.getContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = boardItem.getCreateAt();
        this.writerEmail = boardItem.getWriteEmail();
        this.writerProfileImage = boardItem.getWriterProfileImage();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardListItem boardItem, List<Image> imageList){
        GetBoardResponseDto result = new GetBoardResponseDto(boardItem,imageList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistsBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }



}
