package com.example.medapp.repository;

import com.example.medapp.model.RendezVous;
import com.example.medapp.model.Medecin;
import com.example.medapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatient(Patient patient);
    List<RendezVous> findByMedecin(Medecin medecin);
}
