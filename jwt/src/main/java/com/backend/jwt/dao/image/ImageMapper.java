package com.backend.jwt.dao.image;

import com.backend.jwt.entity.image.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    Integer saveAll(List<Image> images);
    List<Image> findByBoardNumber(Integer boardNumber);
}
