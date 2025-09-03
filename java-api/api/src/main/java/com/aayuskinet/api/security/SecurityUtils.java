package com.aayuskinet.api.security;

import com.aayuskinet.core.entities.AppUser;
import com.aayuskinet.core.interfaces.IAppUserRepository; // Assuming this will be created
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;

public class SecurityUtils {

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("User not authenticated");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername(); // In Spring Security, username is often email
        } else {
            return principal.toString();
        }
    }

    // This method would typically be in a service layer, not a utility class
    // public static AppUser getUserByEmail(IAppUserRepository appUserRepository) {
    //     String email = getCurrentUserEmail();
    //     return appUserRepository.findByEmail(email)
    //             .orElseThrow(() -> new BadCredentialsException("User not found"));
    // }

    // This method would typically be in a service layer, not a utility class
    // public static AppUser getUserByEmailWithAddress(IAppUserRepository appUserRepository) {
    //     String email = getCurrentUserEmail();
    //     return appUserRepository.findByEmailWithAddress(email) // Assuming a custom method in repository
    //             .orElseThrow(() -> new BadCredentialsException("User not found"));
    // }
}
