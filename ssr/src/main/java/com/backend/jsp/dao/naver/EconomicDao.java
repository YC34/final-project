package com.backend.jsp.dao.naver;
import com.backend.jsp.entity.naver.Economic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EconomicDao {
    List<Economic> getUSD();
    List<Economic> getJPY();
    List<Economic> getBTC();
}
