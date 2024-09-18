package com.backend.jsp.dao.naver;

import com.backend.jsp.entity.naver.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
//    Integer write(Comment comment);
    List<Comment> getComment(Integer uid);
    Integer write(Comment comment);
    List<Comment> getReply(Integer commentUid, Integer naverNewsId);
}
