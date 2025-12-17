package com.example.medapp.repository;

import com.example.medapp.model.CategorieMedicament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieMedicamentRepository extends JpaRepository<CategorieMedicament, Long> {
    Optional<CategorieMedicament> findByNom(String nom);
}
