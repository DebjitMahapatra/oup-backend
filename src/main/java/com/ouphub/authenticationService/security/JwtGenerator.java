package com.ouphub.authenticationService.security;

import org.springframework.stereotype.Component;

import com.ouphub.authenticationService.model.JwtAuthenticationToken;
import com.ouphub.authenticationService.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {


    public String generate(JwtUser jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        
        String token= Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
        
        JwtAuthenticationToken jwtToken=new JwtAuthenticationToken(token);
        return jwtToken.getToken();
    }
}
