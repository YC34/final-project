package com.backend.jwt.dto.reqeust;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {

    private String title;
    private String content;
    private List<String> boardImageList;

}
