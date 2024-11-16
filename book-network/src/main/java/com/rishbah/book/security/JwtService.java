package com.rishbah.book.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration ;
    @Value("${application.security.jwt.secret-key}")
    private String secrectKey;

    public String generateJwtToken(UserDetails userDetails) {
        return genrateToken(new HashMap<>(),userDetails);
    }

    private String genrateToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims,userDetails,jwtExpiration);
    }

    private String buildToken(HashMap<String, Object> claims, UserDetails userDetails, long jwtExpiration) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .claim("authorities",authorities)
                .signWith(getSignInKey())
                .compact();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secrectKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String jwt) {
        return "";
    }
}
