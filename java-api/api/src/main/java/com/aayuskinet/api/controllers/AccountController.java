package com.aayuskinet.api.controllers;

import com.aayuskinet.api.dtos.AddressDto;
import com.aayuskinet.api.dtos.LoginDto;
import com.aayuskinet.api.dtos.RegisterDto;
import com.aayuskinet.api.dtos.UserDto;
import com.aayuskinet.api.mappers.AddressMapper;
import com.aayuskinet.api.security.SecurityUtils;
import com.aayuskinet.core.entities.Address;
import com.aayuskinet.core.entities.AppUser;
import com.aayuskinet.core.interfaces.IAppUserRepository;
import com.aayuskinet.core.interfaces.ITokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseApiController {

    private final IAppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;

    public AccountController(IAppUserRepository appUserRepository, PasswordEncoder passwordEncoder,
                             AuthenticationManager authenticationManager, ITokenService tokenService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser user = appUserRepository.findByEmail(loginDto.getEmail()).get();
        String token = tokenService.createToken(user);

        return ResponseEntity.ok(new UserDto(user.getEmail(), user.getFirstName(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterDto registerDto) {
        if (appUserRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        AppUser user = new AppUser();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
    // user.setId should not use email (String) as id (int). Remove or set appropriately if needed.

        appUserRepository.add(user);

        String token = tokenService.createToken(user);
        return ResponseEntity.ok(new UserDto(user.getEmail(), user.getFirstName(), token));
    }

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser() {
        String email = SecurityUtils.getCurrentUserEmail();
        if (email == null) {
            return ResponseEntity.status(401).build();
        }
        AppUser user = appUserRepository.findByEmail(email).get();
        String token = tokenService.createToken(user);
        return ResponseEntity.ok(new UserDto(user.getEmail(), user.getFirstName(), token));
    }

    @GetMapping("/address")
    public ResponseEntity<AddressDto> getUserAddress() {
        String email = SecurityUtils.getCurrentUserEmail();
        if (email == null) {
            return ResponseEntity.status(401).build();
        }
        Optional<AppUser> userOptional = appUserRepository.findByEmailWithAddress(email);

        if (userOptional.isEmpty() || userOptional.get().getAddress() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AddressMapper.toDto(userOptional.get().getAddress()));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto addressDto) {
        String email = SecurityUtils.getCurrentUserEmail();
        if (email == null) {
            return ResponseEntity.status(401).build();
        }
        Optional<AppUser> userOptional = appUserRepository.findByEmailWithAddress(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        AppUser user = userOptional.get();

        if (user.getAddress() == null) {
            user.setAddress(AddressMapper.toEntity(addressDto));
        } else {
            AddressMapper.updateFromDto(user.getAddress(), addressDto);
        }

        appUserRepository.update(user);
        return ResponseEntity.ok(AddressMapper.toDto(user.getAddress()));
    }
}
