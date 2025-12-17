package com.example.medapp.dto.user;

/**
 * DTO de mise à jour du profil utilisateur (champs facultatifs selon le rôle).
 */
public class UserUpdateRequest {
    private String nom;              // nom affiché
    private String telephone;        // téléphone

    // Champs Patient
    private String adresse;
    private String sexe;             // M/F
    private String dateNaissance;    // yyyy-MM-dd

    // Champs Médecin
    private String specialite;
    private String cabinet;
    private String numeroOrdre;

    // Champs Pharmacie
    private String nomCommercial;
    private String registreCommerce;

    public UserUpdateRequest() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
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
