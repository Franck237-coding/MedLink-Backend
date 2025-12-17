package com.example.medapp.controller;

import com.example.medapp.dto.article.ArticleRequest;
import com.example.medapp.model.Article;
import com.example.medapp.security.SecurityUtils;
import com.example.medapp.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/articles")
@Tag(name = "Article", description = "Publication et gestion des articles")
public class ArticleController {

    private final ArticleService articleService;
    private final SecurityUtils securityUtils;

    public ArticleController(ArticleService articleService, SecurityUtils securityUtils) {
        this.articleService = articleService;
        this.securityUtils = securityUtils;
    }

    @PostMapping
    @Operation(summary = "Créer un article")
    public ResponseEntity<Article> create(@Valid @RequestBody ArticleRequest req) {
        String email = securityUtils.currentUsername();
        return ResponseEntity.ok(articleService.create(email, req));
    }

    @GetMapping
    @Operation(summary = "Lister tous les articles")
    public List<Article> all() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un article par id")
    public Article one(@PathVariable Long id) {
        return articleService.get(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un article par id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
