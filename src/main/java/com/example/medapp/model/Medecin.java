package com.example.medapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "medecins")
public class Medecin extends Utilisateur {

    private String specialite;

    private String cabinet;

    @Column(unique = true)
    private String numeroOrdre;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVousList = new ArrayList<>();
}
