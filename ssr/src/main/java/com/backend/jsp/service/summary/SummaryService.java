package com.backend.jsp.service.summary;

import com.backend.jsp.dao.summary.SummaryDao;
import com.backend.jsp.entity.summary.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private final SummaryDao dao;

    @Autowired
    public SummaryService(SummaryDao dao) {
        this.dao = dao;
    }

    public Map<String, Object> getSummary() {
        Map<String, Object> resultMap = new HashMap<>();

        // getTotalCount
        Integer totalCount = dao.getTotalCount();
        resultMap.put("totalCount", totalCount);

        // get 일별 총 수집한 count
        List<Summary> dailyTotal = dao.getDailyTotal();
        resultMap.put("dailyTotal", dailyTotal);


        // get 회사별 count
        List<Summary> companyTotal = dao.getCompanyTotal();
        resultMap.put("companyTotal", companyTotal);

        // get 회사,일별 count
        List<Summary> dailyCompanyTotal = dao.getDailyCompanyTotal();
        resultMap.put("dailyCompanyTotal", dailyCompanyTotal);

        return resultMap;
    }
}
