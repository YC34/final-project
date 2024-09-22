package com.backend.jsp.entity.naver;


import com.backend.jsp.dto.naver.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentUid;
    private String content;
    private String createAt;
    private String deleteAt;
    private String updateAt;
    private String updateYn;
    private String deleteYn;
    private Integer naverNewsId;
    private Integer userId;
    private Integer parentCommentId;
    private String username;
    private String userEmail;


    public Comment(Integer userId, CommentDto dto) {
        this.userId = userId;
        this.content = dto.getContent();
        this.naverNewsId = dto.getBoardId();
        this.parentCommentId = dto.getParentCommentId();
    }
}
