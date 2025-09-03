package com.aayuskinet.api.extensions;

import org.springframework.security.core.context.SecurityContextHolder;

public class ClaimsPrincipleExtensions {
    public static String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
