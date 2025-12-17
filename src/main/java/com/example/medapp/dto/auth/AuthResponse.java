package com.example.medapp.dto.auth;

/**
 * Réponse d'authentification contenant le jeton JWT.
 * Remplacement du record par une classe pour compatibilité Java 8/11.
 */
public class AuthResponse {
    private String token; // jeton JWT

    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
