package com.backend.jwt.dto.object;


import com.backend.jwt.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 아무것도 없는 생성자
@AllArgsConstructor // 모든 필드를 포함하는 생성자
public class BoardListItem {
    private Integer boardNumber;
    private String title;
    private String content;
    private String boardTitleImage;
    private Integer favoriteCount;
    private Integer commentCount;
    private Integer viewCount;
    private String createAt;
    private String writerUsername;
    private String writerProfileImage;
    private String writeEmail;

    public BoardListItem(Board board) {
        this.boardNumber = board.getBoardNumber();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.favoriteCount = board.getFavoriteCount();
        this.commentCount = board.getCommentCount();
        this.viewCount = board.getViewCount();
        this.createAt = board.getCreateAt();
        this.writeEmail=board.getWriteEmail();
    }
}
