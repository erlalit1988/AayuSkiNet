package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITokenService {
    String createToken(AppUser user);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
