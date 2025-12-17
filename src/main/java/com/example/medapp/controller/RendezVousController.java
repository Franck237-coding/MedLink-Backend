package com.example.medapp.controller;

import com.example.medapp.dto.rendezvous.RendezVousRequest;
import com.example.medapp.model.RendezVous;
import com.example.medapp.service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rendezvous")
@Tag(name = "RendezVous", description = "Gestion des rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @PostMapping
    @Operation(summary = "Créer un rendez-vous")
    public ResponseEntity<RendezVous> create(@Valid @RequestBody RendezVousRequest req) {
        return ResponseEntity.ok(rendezVousService.create(req));
    }

    @GetMapping
    @Operation(summary = "Lister tous les rendez-vous")
    public List<RendezVous> all() {
        return rendezVousService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Lister les rendez-vous d'un patient")
    public List<RendezVous> byPatient(@PathVariable Long patientId) {
        return rendezVousService.byPatient(patientId);
    }

    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "Lister les rendez-vous d'un médecin")
    public List<RendezVous> byMedecin(@PathVariable Long medecinId) {
        return rendezVousService.byMedecin(medecinId);
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Mettre à jour le statut d'un rendez-vous")
    public ResponseEntity<RendezVous> updateStatut(@PathVariable Long id, @RequestParam RendezVous.StatutRendezVous statut) {
        return ResponseEntity.ok(rendezVousService.updateStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un rendez-vous par id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rendezVousService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
