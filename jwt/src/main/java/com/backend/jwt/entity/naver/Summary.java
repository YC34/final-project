package com.backend.jwt.entity.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

    private Integer id;
    private Integer flag;
    private String dataType;
    private String date;
    private String company;
    private Integer totalCount;

}
