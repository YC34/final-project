package com.backend.jwt.dao.naver;

import com.backend.jwt.entity.naver.NaverNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    Integer getTotalCount();
    List<NaverNews> getList(Integer numOfRows, Integer offset);
    NaverNews getDetail(Integer naverNewsSequence);

}
