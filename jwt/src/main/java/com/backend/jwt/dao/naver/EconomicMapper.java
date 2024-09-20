package com.backend.jwt.dao.naver;

import com.backend.jwt.entity.naver.EconomicRawData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EconomicMapper {
    boolean existsCode(String code);
    boolean existsType(String eType);
    List<EconomicRawData> getData(String code, String eType,  Integer numOfRows,Integer offset);
}
