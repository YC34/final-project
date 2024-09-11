package com.backend.jwt.dto.object;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListItem {
    private String username;
    private String profileImage;
    private String createAt;
    private String content;


}
