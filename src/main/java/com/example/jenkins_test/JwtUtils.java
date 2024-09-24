package com.example.jenkins_test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {


    public String createAccessToken(String username) {
        return createToken(username, "access", 10000);
    }

    public String createRefreshToken(String username) {
        return createToken(username, "refresh", 100000);
    }

    private String createToken(String username, String type, long expiration) {
        try {
            String secret = "testSecret";

            return JWT.create()
                    .withSubject(type + "token")
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                    .withClaim("username", username)
                    .sign(Algorithm.HMAC512(type + secret));
        } catch (JWTCreationException e) {
            // 토큰 생성 실패 시 예외 처리
            e.printStackTrace();
            return null;
        }
    }

//    public DecodedJWT verifyToken(String token, String type) {
//        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(type + secret)).build();
//            return verifier.verify(token);
//        } catch (JWTVerificationException e) {
//            // 토큰 검증 실패 시 예외 처리
//            e.printStackTrace();
//            return null;
//        }
//    }

//    public String getUsernameFromToken(DecodedJWT decodedToken) {
//        if (decodedJWT == null) {
//            // 토큰 검증 실패 시 처리
//            return null;
//        }
//        return decodedJWT.getClaim("username").asString();
//    }

}
