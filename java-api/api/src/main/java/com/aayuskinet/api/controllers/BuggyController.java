package com.aayuskinet.api.controllers;

import com.aayuskinet.api.dtos.CreateProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buggy")
public class BuggyController extends BaseApiController {

    @GetMapping("unauthorized")
    public ResponseEntity<Void> getUnauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("badrequest")
    public ResponseEntity<String> getBadRequest() {
        return ResponseEntity.badRequest().body("Not a good request");
    }

    @GetMapping("notfound")
    public ResponseEntity<Void> getNotFound() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("internalerror")
    public ResponseEntity<Void> getInternalError() {
        throw new RuntimeException("This is a test exception");
    }

    @PostMapping("validationerror")
    public ResponseEntity<Void> getValidationError(@RequestBody CreateProductDto product) {
        // Validation is handled by @Valid annotation and GlobalExceptionHandler
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("secret")
    public ResponseEntity<String> getSecret() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName(); // Principal name
        String id = authentication.getPrincipal().toString(); // Principal object, might need casting for more details
        return ResponseEntity.ok("Hello " + name + " with the id of " + id);
    }
}
