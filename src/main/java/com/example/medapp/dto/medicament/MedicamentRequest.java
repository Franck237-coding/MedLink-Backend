package com.example.medapp.dto.medicament;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de création/mise à jour d'un médicament.
 * Remplacement du record par une classe pour compatibilité.
 */
public class MedicamentRequest {
    @NotBlank
    private String nom;          // nom du médicament

    private String description;  // description optionnelle

    @NotNull
    private Long pharmacieId;    // identifiant de la pharmacie propriétaire

    private Boolean disponible;  // disponibilité (optionnelle)

    public MedicamentRequest() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getPharmacieId() { return pharmacieId; }
    public void setPharmacieId(Long pharmacieId) { this.pharmacieId = pharmacieId; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}
