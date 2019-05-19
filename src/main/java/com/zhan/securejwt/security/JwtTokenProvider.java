package com.zhan.securejwt.security;

import com.zhan.securejwt.exception.JwtAuthenticationException;
import com.zhan.securejwt.model.MyGrantedAuthorities;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


   @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expired}")
    private int jwtExpirationInMs;


   /* @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }*/

    public String generateToken(Authentication auth) {

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());

        claims.put("roles", getAuthorityNames(userPrincipal));



        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();


    }


    public Long getUserIdFromJwt(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();


        return Long.parseLong(claims.getSubject());

    }

    public String  getUserNameFromJwt(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();


        return claims.getSubject();

    }



    public static void main(String[] args) {


        String secret = "secret";

        Claims claims = Jwts.claims().setSubject("user");

        //claims.put("roles", Arrays.asList("USER"));

        Date now = new Date();

        int expiry = 3600000;

        Date expiryDate = new Date(now.getTime() + 3600000);

        System.out.println(expiryDate);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();




        System.out.println(token);


        try {

            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

        }

        catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }


    }


    public boolean validateToken(String token) {

        System.out.println( "HHHHHHHHHHHHHHHHHHHHHHHH" + Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject());


        try {


            Jwt<Header, Claims> headerClaimsJwt = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
            logger.error("Invalid JWT signature {}", headerClaimsJwt.getBody().getSubject());

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


    public List<String> getAuthorityNames(UserPrincipal userPrincipal) {

        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(authority ->  authority.getAuthority())
                .collect(Collectors.toList());

        return roles;


    }




}
