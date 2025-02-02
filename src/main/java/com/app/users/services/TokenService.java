package com.app.users.services;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${jjwt.secret}")
    private String jjwtSecret;
    private final ConcurrentHashMap<String, Boolean> invalidatedTokens = new ConcurrentHashMap<>();

    public String generateTokens(String id) {
        return Jwts.builder()
                .subject(id)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(24, ChronoUnit.HOURS)))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        return new SecretKeySpec(jjwtSecret.getBytes(), "HmacSHA256");
    }
}
