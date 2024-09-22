package com.backend.jsp.dao.naver;


import com.backend.jsp.entity.naver.NaverNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NaverNewsDao {

    List<NaverNews> getBoardList(Integer pageSize, Integer offset);
    Integer getTotalCount();
    NaverNews getNaverNews(Integer nid);
}
