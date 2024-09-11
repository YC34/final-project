package com.backend.jsp.config.handler;

import com.backend.jsp.exception.EmailAlreadyExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("Login ERROR : " + exception.getMessage());
        log.info("Login ERROR MESSAGE :  " +exception.getMessage());
//
//        if(request.getSession(false) != null){
//            request.getSession().invalidate();
//        }
        String errorMessage = "";
        if (exception instanceof BadCredentialsException) {
            errorMessage =  "정보를 올바르게 입력하세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage =  "계정없음";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "계정만료";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호만료";
        } else if (exception instanceof DisabledException) {
            errorMessage = "계정비활성화";
        } else if (exception instanceof LockedException) {
            errorMessage =  "계정잠김";
        } else{
            errorMessage =  "확인된 에러가 없습니다.";
        }


        HttpSession session = request.getSession(false);
        request.setAttribute("errorMessage", errorMessage);

        if (session != null) {
            session.setAttribute("errorMessage", errorMessage);
        } else {
            // 세션이 없으면 새로 생성
            session = request.getSession(true);
            session.setAttribute("errorMessage", errorMessage);
        }

        response.sendRedirect("/");


    }
}
