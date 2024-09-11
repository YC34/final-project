package com.backend.jwt.entity.board;

import com.backend.jwt.dto.reqeust.BoardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Integer boardNumber; // 게시물 번호 pk auto increase
    private String title; // 게시물 제목
    private String content; // 게시물 내용
    private String createAt; // TODO 게시물 작성 날짜 - type을 어떻게 받을지.
    private Integer favoriteCount; // 게시물 좋아요 수
    private Integer commentCount; // 게시물 댓글 수
    private Integer viewCount; // 게시물 조회 수
    private String writeEmail; // 작성자 이메일
    private String deleteAt; // 삭제 하였을 경우 삭제 날짜.
    private String deleteYn; // 삭제 여부
    private String updateAt; // 업데이트 여부
    private String updateYn; // 업데이트 여부


    public Board(BoardRequestDto dto , String email) {

        // create_at은 쿼리문에서 insert
//        Date now = Date.from(Instant.now());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String createAt = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.content = dto.getContent();
//        this.createAt =createAt;
        this.deleteYn = "N";
        this.favoriteCount =0;
        this.commentCount =0;
        this.viewCount =0;
        this.writeEmail =email;

    }
}
