package com.backend.jsp.service.user;


import com.backend.jsp.dao.user.UserDao;
import com.backend.jsp.entity.user.User;
import com.backend.jsp.entity.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class UserService implements UserDetailsService {
    private Logger log = Logger.getLogger(UserService.class.getName());
    private final UserDao dao;
    private final PasswordEncoder encoder;
    @Autowired
    public UserService(UserDao userDao, PasswordEncoder encoder) {
        this.dao = userDao;
        this.encoder = encoder;
    }

    /**
     *
     * @param user
     * @return int 단일 가입이므로 1이 반환 될것.
     */
    public int signup(User user) throws Exception {
        log.info(user.getUsername());
        // TODO view단에서 중복체크.
        if(dao.existsEmail(user.getUsername())){
            log.info("Email already exists");
            throw new Exception("이메일이 이미 존재합니다.");
        }
        if(dao.existsUsername(user.getMembername())){
            log.info("Username already exists");
            throw new Exception("사용자명이 이미 존재합니다.");
        }
        if(dao.existsTelNumber(user.getTelNumber())){
            log.info("Username already exists");
            throw new Exception("폰 번호가 존재합니다.");
        }

        log.info("요청이 왜 안들어와?");
        log.info(user.getMembername());
        log.info(String.valueOf(!user.getMembername().isEmpty()));


        if(!user.getMembername().isEmpty() && !user.getPassword().isEmpty() && !user.getUsername().isEmpty()){
            log.info("signup user SERVICE");
            user.setPassword(encoder.encode(user.getPassword()));
            if(user.getRole()==null){
                user.setRole("ROLE_"+ UserRole.USER);
                log.info("user role" + user.getRole());
            }
            int count =  dao.signup(user);
            return count;
        }

        return -99999;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = dao.getUserInfo(email);
        if(user == null || String.valueOf(user.getDeleteYn()).equals("Y")){
           throw new UsernameNotFoundException(email);
        }
        return user;
    }

    public int dropUser(String email, String deleteYn) {
       return  dao.dropUser(email,deleteYn);
    }

    public boolean isEmailAvailable(String email) {
        return dao.existsEmail(email);
    }
}
