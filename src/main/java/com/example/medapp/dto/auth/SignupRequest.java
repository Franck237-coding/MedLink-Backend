package com.example.medapp.dto.auth;

import com.example.medapp.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO d'inscription utilisateur (signup) — sans record, avec champs et accesseurs.
 * Les champs spécifiques par rôle sont facultatifs et pris en compte côté service.
 */
public class SignupRequest {
    @Email @NotBlank
    private String email;         // e-mail unique de l'utilisateur

    @NotBlank
    private String password;      // mot de passe en clair (sera hashé)

    @NotBlank
    private String nom;           // nom affiché

    private String telephone;     // numéro de téléphone

    @NotNull
    private Role role;            // rôle cible: PATIENT | MEDECIN | PHARMACIE | ADMIN

    // Champs spécifiques Patient
    private String dateNaissance; // ISO-8601 (yyyy-MM-dd)
    private String adresse;
    private String sexe;          // M/F

    // Champs spécifiques Médecin
    private String specialite;
    private String cabinet;
    private String numeroOrdre;

    // Champs spécifiques Pharmacie
    private String nomCommercial;
    private String registreCommerce;

    public SignupRequest() {}

    // Getters / Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
    public String getCabinet() { return cabinet; }
    public void setCabinet(String cabinet) { this.cabinet = cabinet; }
    public String getNumeroOrdre() { return numeroOrdre; }
    public void setNumeroOrdre(String numeroOrdre) { this.numeroOrdre = numeroOrdre; }
    public String getNomCommercial() { return nomCommercial; }
    public void setNomCommercial(String nomCommercial) { this.nomCommercial = nomCommercial; }
    public String getRegistreCommerce() { return registreCommerce; }
    public void setRegistreCommerce(String registreCommerce) { this.registreCommerce = registreCommerce; }
}
