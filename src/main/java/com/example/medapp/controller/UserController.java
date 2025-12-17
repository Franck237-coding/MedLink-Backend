package com.example.medapp.controller;

import com.example.medapp.model.Utilisateur;
import com.example.medapp.security.SecurityUtils;
import com.example.medapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Profil de l'utilisateur connecté")
public class UserController {

    private final UserService userService;
    private final SecurityUtils securityUtils;

    public UserController(UserService userService, SecurityUtils securityUtils) {
        this.userService = userService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/me")
    @Operation(summary = "Récupérer le profil courant")
    public ResponseEntity<Utilisateur> me() {
        String email = securityUtils.currentUsername();
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/me")
    @Operation(summary = "Mettre à jour le profil courant")
    public ResponseEntity<Utilisateur> update(@Valid @RequestBody Utilisateur patch) {
        String email = securityUtils.currentUsername();
        Utilisateur existing = userService.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        Utilisateur updated = userService.updateProfile(existing, patch);
        return ResponseEntity.ok(updated);
    }
}
