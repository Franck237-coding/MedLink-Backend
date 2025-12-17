package com.example.medapp.repository;

import com.example.medapp.model.Stock;
import com.example.medapp.model.Medicament;
import com.example.medapp.model.Pharmacie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByPharmacieAndMedicament(Pharmacie pharmacie, Medicament medicament);
}
