package com.example.medapp.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO de connexion (login) — remplace l'ancien record par une classe classique.
 * Champs validés via Jakarta Validation.
 */
public class LoginRequest {
    @Email
    @NotBlank
    private String email;    // adresse e-mail de l'utilisateur

    @NotBlank
    private String password; // mot de passe en clair reçu à l'API (sera vérifié)

    public LoginRequest() {}

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
