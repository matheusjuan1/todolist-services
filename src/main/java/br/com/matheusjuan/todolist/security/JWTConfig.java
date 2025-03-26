package br.com.matheusjuan.todolist.security;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "security.config.jwt")
public class JWTConfig {

    private String secretKey;

    private String issuer;

    private Instant creationDate;

    private Instant expirationDate;

    @PostConstruct
    public void init() {
        this.creationDate = ZonedDateTime.now().toInstant();
        this.expirationDate = ZonedDateTime.now().plusDays(2L).toInstant();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }
}
