package com.example.medapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, length = 5000)
    private String contenu;

    @ManyToOne(optional = false)
    private Utilisateur auteur; // Medecin or Admin

    @ManyToOne
    private CategorieArticle categorie;

    private LocalDateTime datePublication = LocalDateTime.now();
}
