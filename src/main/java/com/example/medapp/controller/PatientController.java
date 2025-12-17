package com.example.medapp.controller;

import com.example.medapp.model.Patient;
import com.example.medapp.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient", description = "Gestion des patients")
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    @Operation(summary = "Lister tous les patients")
    public List<Patient> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un patient par id")
    public Patient one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient introuvable"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un patient par id")
    public ResponseEntity<Patient> update(@PathVariable Long id, @Valid @RequestBody Patient patch) {
        Patient existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient introuvable"));

        if (patch.getNom() != null) existing.setNom(patch.getNom());
        if (patch.getTelephone() != null) existing.setTelephone(patch.getTelephone());
        if (patch.getAdresse() != null) existing.setAdresse(patch.getAdresse());
        if (patch.getSexe() != null) existing.setSexe(patch.getSexe());
        if (patch.getDateNaissance() != null) existing.setDateNaissance(patch.getDateNaissance());

        return ResponseEntity.ok(repo.save(existing));
    }
}
