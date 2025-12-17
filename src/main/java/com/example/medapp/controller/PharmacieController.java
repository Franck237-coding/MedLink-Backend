package com.example.medapp.controller;

import com.example.medapp.model.Pharmacie;
import com.example.medapp.repository.PharmacieRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pharmacies")
@Tag(name = "Pharmacie", description = "Gestion des pharmacies")
public class PharmacieController {

    private final PharmacieRepository repo;

    public PharmacieController(PharmacieRepository repo) {
        this.repo = repo;
    }

    // CRUD
    @PostMapping
    @Operation(summary = "Créer une pharmacie")
    public ResponseEntity<Pharmacie> add(@Valid @RequestBody Pharmacie c) {
        return ResponseEntity.ok(repo.save(c));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les pharmacies")
    public List<Pharmacie> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une pharmacie par id")
    public Pharmacie one(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pharmacie introuvable"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une pharmacie")
    public ResponseEntity<Pharmacie> update(
            @PathVariable Long id,
            @Valid @RequestBody Pharmacie p
    ) {
        Pharmacie existing = one(id);

        if (p.getNom() != null) existing.setNom(p.getNom());
        if (p.getTelephone() != null) existing.setTelephone(p.getTelephone());
        if (p.getNomCommercial() != null) existing.setNomCommercial(p.getNomCommercial());
        if (p.getAdresse() != null) existing.setAdresse(p.getAdresse());
        if (p.getRegistreCommerce() != null) existing.setRegistreCommerce(p.getRegistreCommerce());

        return ResponseEntity.ok(repo.save(existing));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une pharmacie")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}






/*package com.example.medapp.controller;

import com.example.medapp.model.Pharmacie;
import com.example.medapp.repository.PharmacieRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping({"/api/pharmacies", "/api/pharmacie"})
@Tag(name = "Pharmacie", description = "Gestion des pharmacies (création via /api/auth/signup)")
public class PharmacieController {

    private final PharmacieRepository repo;

    public PharmacieController(PharmacieRepository repo) {
        this.repo = repo;
    }

    // Note: For creation of a Pharmacie account, use POST /api/auth/signup with role=PHARMACIE
    // Ajout d'un endpoint de création directe pour faciliter les tests Swagger
    @PostMapping
    @Operation(summary = "Créer une pharmacie (alias: add)")
    public ResponseEntity<Pharmacie> add(@Valid @RequestBody Pharmacie c) {
        return ResponseEntity.ok(repo.save(c));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les pharmacies (alias: GetAll)")
    public List<Pharmacie> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une pharmacie par id (alias: Get/{id})")
    public Pharmacie one(@PathVariable Long id) { return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Pharmacie introuvable")); }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une pharmacie par id (alias: Update/{id})")
    public ResponseEntity<Pharmacie> update(@PathVariable Long id, @Valid @RequestBody Pharmacie patch) {
        Pharmacie existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Pharmacie introuvable"));
        existing.setNom(patch.getNom() != null ? patch.getNom() : existing.getNom());
        existing.setTelephone(patch.getTelephone() != null ? patch.getTelephone() : existing.getTelephone());
        existing.setNomCommercial(patch.getNomCommercial() != null ? patch.getNomCommercial() : existing.getNomCommercial());
        existing.setAdresse(patch.getAdresse() != null ? patch.getAdresse() : existing.getAdresse());
        existing.setRegistreCommerce(patch.getRegistreCommerce() != null ? patch.getRegistreCommerce() : existing.getRegistreCommerce());
        return ResponseEntity.ok(repo.save(existing));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une pharmacie par id (alias: Delete/{id})")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- Alias endpoints pour Swagger ---

    @GetMapping({"/GetAll", "/getAll"})
    @Operation(summary = "[Alias] GetAll - Lister toutes les pharmacies")
    public List<Pharmacie> getAllAlias() { return repo.findAll(); }

    @GetMapping({"/Get/{id}", "/get/{id}"})
    @Operation(summary = "[Alias] Get/{id} - Récupérer une pharmacie par id")
    public Pharmacie getOneAlias(@PathVariable Long id) { return one(id); }

    @PutMapping({"/Update/{id}", "/update/{id}"})
    @Operation(summary = "[Alias] Update/{id} - Mettre à jour une pharmacie")
    public ResponseEntity<Pharmacie> updateAlias(@PathVariable Long id, @Valid @RequestBody Pharmacie patch) { return update(id, patch); }

    @DeleteMapping({"/Delete/{id}", "/delete/{id}"})
    @Operation(summary = "[Alias] Delete/{id} - Supprimer une pharmacie")
    public ResponseEntity<Void> deleteAlias(@PathVariable Long id) { return delete(id); }

    @PostMapping({"/Add", "/add"})
    @Operation(summary = "[Alias] Add - Créer une pharmacie")
    public ResponseEntity<Pharmacie> addAlias(@Valid @RequestBody Pharmacie c) { return add(c); }
}
*/