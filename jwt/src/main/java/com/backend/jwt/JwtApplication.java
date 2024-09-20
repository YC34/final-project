package com.backend.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// default config 되는 문제 해결 방법 1.
//@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
