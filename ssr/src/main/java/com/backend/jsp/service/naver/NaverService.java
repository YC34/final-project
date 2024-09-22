package com.backend.jsp.service.naver;

import com.backend.jsp.dao.naver.CommentDao;
import com.backend.jsp.dao.naver.NaverNewsDao;
import com.backend.jsp.dao.user.UserDao;
import com.backend.jsp.dto.naver.CommentDto;
import com.backend.jsp.entity.naver.Comment;
import com.backend.jsp.entity.naver.Economic;
import com.backend.jsp.entity.naver.NaverNews;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.backend.jsp.dao.naver.EconomicDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NaverService {

    private final EconomicDao economicDao;
    private final NaverNewsDao naverNewsDao;
    private final CommentDao commentDao;
    private final UserDao userDao;

    public NaverService(EconomicDao economicDao, NaverNewsDao naverNewsDao, CommentDao commentDao, UserDao userDao) {
        this.economicDao = economicDao;
        this.naverNewsDao = naverNewsDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    public Map<String, Object>  getNaverNews(Integer uid){
        Map<String, Object> result = new HashMap<>();

            // get naver detail info
            NaverNews naverNews = naverNewsDao.getNaverNews(uid);
            result.put("naverNews",naverNews);
            // get Comment detail comment
            List<Comment> comments = commentDao.getComment(uid);
            result.put("commentList",comments);
            // get CommentReply
            Map<Integer, List<Comment>> replayMap = new HashMap<>();
            for (Comment comment : comments){
                if (comment.getParentCommentId() == null) {
                    // 댓글의 ID와 뉴스 ID를 사용하여 대댓글 조회
                    List<Comment> replies = commentDao.getReply(comment.getCommentUid(), uid);
                    replayMap.put(comment.getCommentUid(), replies);
                }
            }
            result.put("replyList",replayMap);

        return result;
    }

//    public NaverNews getNaverNews(Integer nid) {
//        return naverNewsDao.getNaverNews(nid);
//    }



    public static int pageSize = 10; // 한 페이지에 보여줄 수
    public Map<String, List<?>> getBoardList(int pageNum) {

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

    public Integer writeComment(CommentDto dto) {
        // get userId
        Integer userId;
        try {
            log.info("userEmail : {}", dto.getUserEmail());
             userId = userDao.getUserId(dto.getUserEmail());
             log.info("userId : {}", userId);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return -99999;
        }
        Comment comment =
                new Comment(userId,dto);
        Integer result = commentDao.write(comment);

        return result;
    }

    public Integer deleteComment(Integer commentUid) {
        Integer result = commentDao.deleteComment(commentUid);
        return result;
    }

    public Integer getNaverNewsId(Integer commentUid) {
        return commentDao.getNaverNewsId(commentUid);
    }
}
