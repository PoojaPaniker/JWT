package com.myproject.blog.myprojectblog.security;

import com.myproject.blog.myprojectblog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.*;
import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${app.jwt-secret}")
    private String JWTSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int exp;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        /*LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime expired = currentDate.plus(exp,ChronoUnit.MILLIS);*/
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + exp);
        String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).
                setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, JWTSecret).compact
                        ();
        return token;
    }

    public String getUserNameFromJWt(String token) {

        Claims claims = Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();

    }

    public boolean validateJWTToken(String token){

        try {
            Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(token);
            return true;
        }catch(SignatureException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"JWT SIgnature invalid");

        }catch(MalformedJwtException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid Token");
        }catch (ExpiredJwtException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Token Expired");
        }catch(UnsupportedJwtException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Unsupported JWT Exception");
        }catch(IllegalArgumentException e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT Token claims");
        }

    }


}
