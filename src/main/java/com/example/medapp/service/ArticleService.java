package com.example.medapp.service;

import com.example.medapp.dto.article.ArticleRequest;
import com.example.medapp.model.*;
import com.example.medapp.repository.ArticleRepository;
import com.example.medapp.repository.CategorieArticleRepository;
import com.example.medapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategorieArticleRepository categorieArticleRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ArticleService(ArticleRepository articleRepository, CategorieArticleRepository categorieArticleRepository, UtilisateurRepository utilisateurRepository) {
        this.articleRepository = articleRepository;
        this.categorieArticleRepository = categorieArticleRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Création d'un article par un auteur (médecin/admin) à partir d'un DTO.
     */
    public Article create(String authorEmail, ArticleRequest req) {
        Utilisateur auteur = utilisateurRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new IllegalArgumentException("Auteur introuvable"));
        if (!(auteur.getRole() == Role.MEDECIN || auteur.getRole() == Role.ADMIN)) {
            throw new IllegalArgumentException("Seuls les médecins ou administrateurs peuvent publier des articles");
        }
        Article a = new Article();
        a.setTitre(req.getTitre());
        a.setContenu(req.getContenu());
        a.setAuteur(auteur);
        if (req.getCategorieId() != null) {
            CategorieArticle cat = categorieArticleRepository.findById(req.getCategorieId())
                    .orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable"));
            a.setCategorie(cat);
        }
        return articleRepository.save(a);
    }

    public List<Article> findAll() { return articleRepository.findAll(); }

    public Article get(Long id) { return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Article introuvable")); }

    public void delete(Long id) { articleRepository.deleteById(id); }
}
