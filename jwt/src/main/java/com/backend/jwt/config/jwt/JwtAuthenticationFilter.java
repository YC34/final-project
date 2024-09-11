package com.backend.jwt.config.jwt;

import com.backend.jwt.common.JwtExceptionCode;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor // 필수 생성자 지정 final
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // reqeust token꺼내오기.
        String accessToken = parseBearerToken(request);
        if( accessToken == null){
            filterChain.doFilter(request, response);
            return;
        }
        log.info("JWT Authentication Token: {}", accessToken);
        String email = "";
        String role = "";
        try {
             // 메일 , 권한 뽑아오기.
             email = jwtUtil.getEmailAccess(accessToken);
             role = jwtUtil.getRoleAccess(accessToken);
             log.info("JWT Role: {}", role);
             // 만료여부.
             jwtUtil.isExpiredAccess(accessToken);
        }catch (NullPointerException | IllegalArgumentException e){
            request.setAttribute("exception",JwtExceptionCode.UNSUPPORTED_TOKEN);
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            request.setAttribute("exception",JwtExceptionCode.TOKEN_EXPIRED);
            filterChain.doFilter(request, response);
        }catch (Exception e){
            request.setAttribute("exception",JwtExceptionCode.UNKNOWN_ERROR);
            filterChain.doFilter(request, response);
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        log.info("JWT Authentication Token Authorities: {}", authorities);
        AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);

    }

    // headers에서 token값 빼오기.
    private String parseBearerToken(HttpServletRequest request) {

        // 1.request에서 header 가져오기
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null ) return null;

        // 2. 가져온 토큰이 bearer인지 확인
        boolean isBearer = authHeader.startsWith("Bearer ");
        if(!isBearer) return null;

        // 3. 토큰에서 진짜 토큰값만 빼오기.
//        String accessToken = authHeader.replace("Bearer ", "");
        return authHeader.replace("Bearer ", "");

    }
}
