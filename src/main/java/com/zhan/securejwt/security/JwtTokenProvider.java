package com.zhan.securejwt.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


   // @Value("@{app.jwtSecret}")
    private String jwtSecret = "JWTSuperSecretKey";

 //@Value("@{app.jwtExpirationInMs}")
    private int jwtExpirationInMs = 604800000;


    public String generateToken(Authentication auth) {

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);


        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }


    public Long getUserIdFromJwt(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();


        return Long.parseLong(claims.getSubject());

    }


    public static void main(String[] args) {




        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTU4MDA3MjI3LCJleHAiOjE1NTg2MTIwMjd9.jALu7qTDrYYrEcz9bnhik8dE4DpGAzFlztrAvfXukVvNJFoZKmpF6wERz9WAlZ77ZeE8J_fkTCt6rHKDb1pxYA";


        Claims claims = Jwts.parser()
                .setSigningKey("JWTSuperSecretKey")
                .parseClaimsJws(token)
                .getBody();

        System.out.println(new JwtTokenProvider().validateToken(token));


    }


    public boolean validateToken(String token) {

        try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
        return  true;
        }

        catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;

    }





}
