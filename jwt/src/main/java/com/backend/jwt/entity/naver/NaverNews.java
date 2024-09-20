package com.backend.jwt.entity.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
    private String imgUrl;
    private String summaryYn;

}
