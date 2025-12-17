package com.example.medapp.repository;

import com.example.medapp.model.Pharmacie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacieRepository extends JpaRepository<Pharmacie, Long> {
}
