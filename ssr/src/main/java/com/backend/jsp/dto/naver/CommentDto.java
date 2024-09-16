package com.backend.jsp.dto.naver;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String userEmail;
    private String content;
    private Integer boardId;
    private Integer parentCommentId;
}
