package com.rishbah.book.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class JwtService {

    public String generateJwtToken(UserDetails userDetails) {
        return genrateToken(new HashMap<>(),userDetails);
    }

    


    public String extractUsername(String jwt) {
        return "";
    }
}
