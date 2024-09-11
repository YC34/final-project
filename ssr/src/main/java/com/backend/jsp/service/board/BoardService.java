package com.backend.jsp.service.board;

import com.backend.jsp.dao.board.NaverNewsDao;
import com.backend.jsp.entity.board.Economic;
import com.backend.jsp.entity.board.NaverNews;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.backend.jsp.dao.board.EconomicDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BoardService {

    private final EconomicDao economicDao;
    private final NaverNewsDao naverNewsDao;

    public BoardService(EconomicDao economicDao, NaverNewsDao naverNewsDao) {
        this.economicDao = economicDao;
        this.naverNewsDao = naverNewsDao;
    }



    public NaverNews getNaverNews(Integer nid) {
        return naverNewsDao.getNaverNews(nid);
    }

    public static int pageSize = 10; // 한 페이지에 보여줄 수
    public Map<String, List<?>> getBoardList(int pageNum) throws JsonProcessingException {

        Map<String, List<?>> result = new HashMap<>();

            // start paging
            Integer totalCount = naverNewsDao.getTotalCount();
            Integer totalPage = (totalCount + pageSize - 1) / pageSize;

            List<Integer> pageInfo = new ArrayList<>();
            pageInfo.add(pageNum);
            pageInfo.add(totalPage);

        result.put("pageInfo", pageInfo);
            // end paging
            // start newsList
            Integer offset = (pageNum - 1) * pageSize;
            List<NaverNews> newsList = naverNewsDao.getBoardList(pageSize, offset);

        result.put("naverNewsList", newsList);
            // end newsList


            // start economicList
            List<Economic> usdDataList = economicDao.getUSD();
            List<Economic> jpyDataList = economicDao.getJPY();
            List<Economic> btcDataList = economicDao.getBTC();
            for (Economic jpy : jpyDataList) {
                jpy.setCloseVol(jpy.getCloseVol() * 100);
            }


           List<String> economicList = new ArrayList<>();
           try {
                ObjectMapper mapper = new ObjectMapper();
                String usdDataListJson = mapper.writeValueAsString(usdDataList);
                String jpyDataListJson = mapper.writeValueAsString(jpyDataList);
                String btcDataListJson = mapper.writeValueAsString(btcDataList);
                economicList.add(usdDataListJson);
                economicList.add(jpyDataListJson);
                economicList.add(btcDataListJson);
           }catch (JsonProcessingException e){
                e.printStackTrace();
                log.error(e.getMessage());
           }
            // end economicList

        result.put("economicDataList", economicList);
        return result;
    }
}
