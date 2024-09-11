package com.backend.jwt.config.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtUtil {
    // JWT 구성
    // header , payload, signature
    // aaaaa.bbbbb.ccccc

    private SecretKey accessSecretKey;
    private SecretKey refreshSecretKey;

    @Value("${spring.jwt.refresh_time}")
    private Long refreshTime;

    @Value("${spring.jwt.access_time}")
    private Long accessTime;


    public JwtUtil( @Value("${spring.jwt.access_secret}") String accessSecret ,
                    @Value("${spring.jwt.refresh_secret}") String refreshSecret) {

        accessSecretKey = new SecretKeySpec(accessSecret.getBytes(StandardCharsets.UTF_8)
                ,Jwts.SIG.HS256.key().build().getAlgorithm());
        refreshSecretKey = new SecretKeySpec(refreshSecret.getBytes(StandardCharsets.UTF_8)
                ,Jwts.SIG.HS256.key().build().getAlgorithm());

    }
    // accessToken
    public String getEmailAccess(String token) {
            return Jwts.parser().verifyWith(accessSecretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getRoleAccess(String token) {
        return Jwts.parser().verifyWith(accessSecretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    public Integer getUserIdAccess(String token) {
        return Jwts.parser().verifyWith(accessSecretKey).build().parseSignedClaims(token).getPayload().get("userId", Integer.class);
    }

    public Boolean isExpiredAccess(String token) {
            return Jwts.parser().verifyWith(accessSecretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // refresh token
    public String getEmailRefresh(String token){
        return Jwts.parser().verifyWith(refreshSecretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getRoleRefresh(String token){
        return Jwts.parser().verifyWith(refreshSecretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Integer getUserIdRefresh(String token){
        return Jwts.parser().verifyWith(refreshSecretKey).build().parseSignedClaims(token).getPayload().get("userId", Integer.class);
    }

    public Boolean isExpiredRefresh(String token){
        return Jwts.parser().verifyWith(refreshSecretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // TODO type은 제외...? 만약 refresh와 access token 둘다 db에서 관리한다면 추가.
    // TODO 소셜 로그인 구현할때..?
    //    public String getType(String token){
    //        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("tokenType", String.class);
    //    }

    public String createAccessToken(String email,String role, Integer userId) {
        return Jwts.builder()
                .claim("email",email)
                .claim("role",role)
                .claim("userId",userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTime))
                .signWith(accessSecretKey)
                .compact();
    }

    public String createRefreshToken(String email,String role,Integer userId) {
        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .claim("userId",userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTime))
                .signWith(refreshSecretKey)
                .compact();
    }





}
