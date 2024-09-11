package com.backend.jwt.config;

import com.backend.jwt.common.JwtExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 인증실패시 오류에 대한 처리.
@Component
@Slf4j
public class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String excetion = (String) request.getAttribute("exception");
        String message = "";
        log.error("AuthenticationEntryPoint Exception {}",excetion);
        if (excetion == null) {
            log.error("exception is null");
            message="알수없는 오류입니다.";
            setResponse(response,JwtExceptionCode.UNKNOWN_ERROR,message);
        } else if (excetion.equals(JwtExceptionCode.TOKEN_EXPIRED)) {
            log.error("token expired");
            message ="access token이 만료되었습니다.";
            setResponse(response,JwtExceptionCode.TOKEN_EXPIRED,message);
        }else if (excetion.equals(JwtExceptionCode.TOKEN_INVALID)) {
            log.error("token invalid");
            message = "token invalid.";
            setResponse(response,JwtExceptionCode.TOKEN_INVALID,message);
        }


    }

    private void setResponse(HttpServletResponse response, String exceptionCode,String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, String> error = new HashMap<>();
        error.put("message",message);
        error.put("code",exceptionCode);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(error);
        response.getWriter().write(jsonResponse);
    }
}
