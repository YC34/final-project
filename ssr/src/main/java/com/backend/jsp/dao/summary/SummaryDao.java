package com.backend.jsp.dao.summary;

import com.backend.jsp.entity.summary.Summary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SummaryDao {

    Integer getTotalCount();
    List<Summary> getDailyTotal();
    List<Summary> getCompanyTotal();
    List<Summary> getDailyCompanyTotal();
}
