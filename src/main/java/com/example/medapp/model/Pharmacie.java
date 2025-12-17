package com.example.medapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "pharmacies")
public class Pharmacie extends Utilisateur {

    private String nomCommercial;

    private String adresse;

    @Column(unique = true)
    private String registreCommerce;

    @OneToMany(mappedBy = "pharmacie")
    private List<Medicament> medicaments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pharmacie_patients",
            joinColumns = @JoinColumn(name = "pharmacie_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients = new ArrayList<>();
}
