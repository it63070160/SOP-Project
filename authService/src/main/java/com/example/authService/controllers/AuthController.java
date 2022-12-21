package com.example.authService.controllers;

import com.example.authService.entities.AuthRequest;
import com.example.authService.entities.AuthResponse;
import com.example.authService.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> logIn(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.logIn(authRequest));
    }
}
