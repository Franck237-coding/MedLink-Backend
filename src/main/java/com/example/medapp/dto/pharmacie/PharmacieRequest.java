package com.example.medapp.dto.pharmacie;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO de création/mise à jour d'une pharmacie via API (si nécessaire).
 */
public class PharmacieRequest {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String nom;             // nom d'utilisateur ou affiché

    private String telephone;

    @NotBlank
    private String nomCommercial;   // raison sociale

    private String adresse;

    private String registreCommerce;

    private String password;        // mot de passe en clair pour création de compte

    public PharmacieRequest() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getNomCommercial() { return nomCommercial; }
    public void setNomCommercial(String nomCommercial) { this.nomCommercial = nomCommercial; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getRegistreCommerce() { return registreCommerce; }
    public void setRegistreCommerce(String registreCommerce) { this.registreCommerce = registreCommerce; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
