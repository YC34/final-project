package com.backend.jwt.entity.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {


    private Integer boardSequence;
    private String type;
    private Integer boardNumber;
    private String image;
    public Image(String type , Integer boardNumber , String image) {
        this.type = type;
        this.boardNumber = boardNumber;
        this.image = image;
    }
}
