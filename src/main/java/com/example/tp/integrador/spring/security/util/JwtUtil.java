package com.example.tp.integrador.spring.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String privateKey;

    @Value("${USER_GENERATOR}")
    private String userGenerator;

    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        String username = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000)))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return jwtToken;
    }

    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException jwtVerificationException){
            throw new JWTVerificationException("Token invalido");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecifClaim(DecodedJWT decodedJWT, String claimname){
        return decodedJWT.getClaim(claimname);
    }

    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
