package com.example.medapp.dto.rendezvous;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * DTO de création d'un rendez-vous (patient, médecin, date/heure).
 */
public class RendezVousRequest {
    @NotNull
    private Long patientId;     // identifiant du patient

    @NotNull
    private Long medecinId;     // identifiant du médecin

    @NotNull
    @Future
    private LocalDateTime dateHeure; // date et heure du rendez-vous

    public RendezVousRequest() {}

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getMedecinId() { return medecinId; }
    public void setMedecinId(Long medecinId) { this.medecinId = medecinId; }
    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
}
