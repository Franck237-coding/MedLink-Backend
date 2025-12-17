package com.example.medapp.controller;

import com.example.medapp.model.CategorieArticle;
import com.example.medapp.repository.CategorieArticleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "CategorieArticle", description = "Gestion des catégories d'articles")
public class CategorieArticleController {

    private final CategorieArticleRepository repo;

    public CategorieArticleController(CategorieArticleRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @Operation(summary = "Créer une catégorie")
    public ResponseEntity<CategorieArticle> create(@Valid @RequestBody CategorieArticle c) {
        return ResponseEntity.ok(repo.save(c));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une catégorie par id")
    public ResponseEntity<CategorieArticle> update(@PathVariable Long id, @Valid @RequestBody CategorieArticle c) {
        CategorieArticle existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable"));
        existing.setNom(c.getNom());
        existing.setDescription(c.getDescription());
        return ResponseEntity.ok(repo.save(existing));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les catégories")
    public List<CategorieArticle> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une catégorie par id")
    public CategorieArticle one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une catégorie par id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
