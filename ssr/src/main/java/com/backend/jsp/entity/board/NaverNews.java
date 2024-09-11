package com.backend.jsp.entity.board;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaverNews {

    private Integer naverNewsSequence;
    private String title;
    private String url;
    private String contents;
    private String company;
    private String newsDate;
    private String createAt;
}
