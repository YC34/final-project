package com.backend.jsp.dao.user;

import com.backend.jsp.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    boolean existsEmail(String email);
    boolean existsUsername(String memberName);
    boolean existsTelNumber(String telNumber);
    int signup(User user);
    User getUserInfo(String email);
    int dropUser(String email, String deleteYn);

    Integer getUserId(String userEmail);


}
