package com.backend.jsp.dao.board;


import com.backend.jsp.entity.board.NaverNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NaverNewsDao {

    List<NaverNews> getBoardList(Integer totalPage, Integer offset);
    Integer getTotalCount();
    NaverNews getNaverNews(Integer nid);
}
