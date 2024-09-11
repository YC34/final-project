package com.backend.jsp.config;


import com.backend.jsp.config.handler.CustomFailureHandler;
import com.backend.jsp.config.handler.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private String[] allowURLs = {"/","/users/login","/users/signup","/board/**","/chart/**","/css/**"};
//    private String[] allowViews = {"/WEB-INF/views/index.jsp","/WEB-INF/views/users/login-page.jsp","/WEB-INF/views/users/signup-page.jsp","/WEB-INF/views/board/board-news-page.jsp"};


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
//                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        (auth)->{
                            auth.requestMatchers(allowURLs).permitAll()
                                .requestMatchers("/WEB-INF/views/**").permitAll()
                                .anyRequest().authenticated();
                        });

        http.formLogin(
                (login)->{
                    login.loginPage("/users/login")
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new CustomSuccessHandler())
                    .failureHandler(new CustomFailureHandler()).permitAll();
                });

        http
                .logout((logout)->{
                    logout.logoutUrl("/users/logout");
                    logout.logoutSuccessUrl("/");
                    logout.invalidateHttpSession(true);
                });

        return http.build();
    }

}
