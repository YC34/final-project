package com.backend.jwt.entity.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {


    private Integer boardSequence;
    private Integer boardNumber;
    private String image;

    public Image(Integer boardNumber , String image) {
        this.boardNumber = boardNumber;
        this.image = image;
    }
}
