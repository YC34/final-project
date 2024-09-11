package com.backend.jwt.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // REACT 와 연결하는 부분 config

    // cors
    // 3000번 포트에서는 api호출 가능.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE","PATCH")
                .allowCredentials(true);
//                .exposedHeaders("access");
    }





}
