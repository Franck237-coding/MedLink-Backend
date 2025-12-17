package com.example.medapp.controller;

import com.example.medapp.model.Medecin;
import com.example.medapp.repository.MedecinRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/medecins")
@Tag(name = "Medecin", description = "Gestion des médecins")
public class MedecinController {

    private final MedecinRepository repo;

    public MedecinController(MedecinRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    @Operation(summary = "Lister tous les médecins")
    public List<Medecin> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un médecin par id")
    public Medecin one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Médecin introuvable"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un médecin par id")
    public ResponseEntity<Medecin> update(@PathVariable Long id, @Valid @RequestBody Medecin patch) {
        Medecin existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Médecin introuvable"));

        if (patch.getNom() != null) existing.setNom(patch.getNom());
        if (patch.getTelephone() != null) existing.setTelephone(patch.getTelephone());
        if (patch.getSpecialite() != null) existing.setSpecialite(patch.getSpecialite());
        if (patch.getCabinet() != null) existing.setCabinet(patch.getCabinet());
        if (patch.getNumeroOrdre() != null) existing.setNumeroOrdre(patch.getNumeroOrdre());

        return ResponseEntity.ok(repo.save(existing));
    }
}
