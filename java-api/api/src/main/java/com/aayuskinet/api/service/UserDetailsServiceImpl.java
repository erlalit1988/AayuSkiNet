package com.aayuskinet.api.service;

import com.aayuskinet.core.entities.AppUser;
import com.aayuskinet.core.interfaces.IAppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IAppUserRepository appUserRepository;

    public UserDetailsServiceImpl(IAppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        AppUser appUser = userOptional.get();
        return new User(appUser.getEmail(), appUser.getPassword(), new ArrayList<>());
    }
}
