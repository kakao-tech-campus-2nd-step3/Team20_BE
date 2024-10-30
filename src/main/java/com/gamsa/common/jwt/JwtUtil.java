package com.gamsa.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    /**
     * secret과 알고리즘을 저장하여 Secretkey에 저장
     */
    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(
            secret.getBytes(StandardCharsets.UTF_8),
            SIG.HS256.key().build().getAlgorithm()
        );
    }

    /**
     * 토큰을 통해 payload 정보 반환
     */
    public Long getUserId(String token) {
        return validateJwt(token)
            .getPayload()
            .get("userId", Long.class);
    }

    /**
     * 토큰을 통해 만료가 되었는지 확인
     */
    public boolean isExpired(String token) {
        return validateJwt(token)
            .getPayload()
            .getExpiration()
            .before(new Date());
    }

    public String createJwt(Long userId, long expirationMs) {
        return Jwts.builder()
            .claim("userId", userId)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(secretKey)
            .compact();
    }

    private Jws<Claims> validateJwt(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token);
    }
}
