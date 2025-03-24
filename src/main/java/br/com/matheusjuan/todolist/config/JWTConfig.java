package br.com.matheusjuan.todolist.config;

import java.security.Key;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    
    private String secretKey;

    private Long expirationTime;

    private Key signingKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = this.secretKey.getBytes();
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expiration) {
        this.expirationTime = expiration;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}
