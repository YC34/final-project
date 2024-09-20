
package com.backend.jwt.config;


import com.backend.jwt.config.jwt.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 허용가능한 url명시
    private final String [] allowURLs = {"/","/api/v1/auth/log-in","/api/v1/auth/sign-up","/api/v1/auth/refresh-token","/api/v1/search/**","/file/**"};

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final FailedAuthenticationEntryPoint failedAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, FailedAuthenticationEntryPoint failedAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.failedAuthenticationEntryPoint = failedAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    // CORS config는 WebConfig에 해둘 예정
    // csrf는 session을 유지하지 않기 때문에 disable

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf( CsrfConfigurer::disable)
                .httpBasic( HttpBasicConfigurer::disable)
                .formLogin( FormLoginConfigurer::disable);
        http
                .authorizeRequests(
                        (auth) ->{
                            auth.requestMatchers(allowURLs).permitAll()
                                 .requestMatchers(HttpMethod.GET,"/api/v1/naver/**","/api/v1/users/**").permitAll()
                                 .anyRequest().authenticated();
                        });


        // 인증 실패 예외 처리.
        http
                .exceptionHandling(
                        exception -> {
                            exception.authenticationEntryPoint(failedAuthenticationEntryPoint);
                        });

        // session 상태유지 하지 않음.
        http
                .sessionManagement((session) ->{
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        });

        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}

