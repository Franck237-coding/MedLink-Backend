package com.example.medapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "patients")
public class Patient extends Utilisateur {

    private LocalDate dateNaissance;

    private String adresse;

    @Column(length = 1)
    private String sexe; // M/F

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVousList = new ArrayList<>();

    @ManyToMany(mappedBy = "patients")
    private List<Pharmacie> pharmacies = new ArrayList<>();
}
