package com.example.medapp.controller;

import com.example.medapp.dto.auth.AuthResponse;
import com.example.medapp.dto.auth.LoginRequest;
import com.example.medapp.dto.auth.SignupRequest;
import com.example.medapp.model.Utilisateur;
import com.example.medapp.security.JwtUtil;
import com.example.medapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Identifiants invalides");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Utilisateur> signup(@Valid @RequestBody SignupRequest request) {
        Utilisateur created = authService.signup(request);
        return ResponseEntity.ok(created);
    }
}
