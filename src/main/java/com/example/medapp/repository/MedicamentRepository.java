package com.example.medapp.repository;

import com.example.medapp.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    List<Medicament> findByNomContainingIgnoreCase(String nom);
    List<Medicament> findByDisponibleTrue();
}
