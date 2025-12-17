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
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "rendez_vous_id", unique = true, nullable = false)
    private RendezVous rendezVous;

    @Column(length = 2000)
    private String diagnostic;

    @Column(length = 2000)
    private String prescription;

    private LocalDateTime dateConsultation = LocalDateTime.now();
}
