package br.com.matheusjuan.todolist.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.matheusjuan.todolist.error.AuthExceptions.JWTCreationException;
import br.com.matheusjuan.todolist.error.AuthExceptions.JWTVerificationException;

@Service
public class JWTCreator {

    @Autowired
    private JWTConfig config;

    public String generateToken(UUID idUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(config.getSecretKey());
            return JWT.create()
                    .withIssuer(config.getIssuer())
                    .withIssuedAt(config.getCreationDate())
                    .withExpiresAt(config.getExpirationDate())
                    .withSubject(idUser.toString())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new JWTCreationException();
        }

    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(config.getSecretKey());
            return JWT.require(algorithm)
                    .withIssuer(config.getIssuer())
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new JWTVerificationException();
        }

    }
}
