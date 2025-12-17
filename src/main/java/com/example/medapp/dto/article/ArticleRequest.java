package com.example.medapp.dto.article;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO pour la création d'un article de santé publique.
 */
public class ArticleRequest {
    @NotBlank
    private String titre;      // titre de l'article

    @NotBlank
    private String contenu;    // contenu de l'article

    private Long categorieId;  // identifiant de la catégorie (facultatif)

    public ArticleRequest() {}

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public Long getCategorieId() { return categorieId; }
    public void setCategorieId(Long categorieId) { this.categorieId = categorieId; }
}
