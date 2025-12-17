package com.example.medapp.controller;

import com.example.medapp.dto.medicament.MedicamentRequest;
import com.example.medapp.model.Medicament;
import com.example.medapp.service.MedicamentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/medicaments")
@Tag(name = "Medicament", description = "CRUD et recherche des médicaments")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @PostMapping
    @Operation(summary = "Créer un médicament")
    public ResponseEntity<Medicament> create(@Valid @RequestBody MedicamentRequest req) {
        return ResponseEntity.ok(medicamentService.create(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un médicament par id")
    public ResponseEntity<Medicament> update(@PathVariable Long id, @Valid @RequestBody MedicamentRequest req) {
        return ResponseEntity.ok(medicamentService.update(id, req));
    }

    @GetMapping
    @Operation(summary = "Lister tous les médicaments")
    public List<Medicament> all() {
        return medicamentService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un médicament par id")
    public Medicament one(@PathVariable Long id) {
        return medicamentService.get(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des médicaments par nom")
    public List<Medicament> search(@RequestParam("q") String q) {
        return medicamentService.searchByName(q);
    }

    @GetMapping("/available")
    @Operation(summary = "Lister les médicaments disponibles")
    public List<Medicament> available() {
        return medicamentService.available();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un médicament par id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
