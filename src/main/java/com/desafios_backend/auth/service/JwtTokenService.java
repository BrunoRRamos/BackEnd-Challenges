package com.desafios_backend.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.desafios_backend.auth.middleware.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "secret";
    private static final String ISSUER = "pizzurg-api";
    private final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(UserDetailsImpl user) {
        try {

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(ALGORITHM);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while creating token", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            return JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Expired or invalid token.", exception);
        }
    }

    private Instant creationDate() {
        return ZonedDateTime
                .now(ZoneId.of("America/Recife"))
                .toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime
                .now(ZoneId.of("America/Recife"))
                .plusHours(4)
                .toInstant();
    }
}
