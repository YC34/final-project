package com.backend.jsp.dao.board;
import com.backend.jsp.entity.board.Economic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EconomicDao {
    List<Economic> getUSD();
    List<Economic> getJPY();
    List<Economic> getBTC();
}
