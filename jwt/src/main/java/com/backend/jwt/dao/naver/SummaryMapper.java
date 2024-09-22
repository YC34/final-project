package com.backend.jwt.dao.naver;

import com.backend.jwt.dto.reqeust.naver.SummaryRequestDto;
import com.backend.jwt.entity.naver.Summary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SummaryMapper {
    boolean existsFlag(Integer flag);
    boolean existsType(String dataType);
    List<Summary> getData(SummaryRequestDto dto);
}
