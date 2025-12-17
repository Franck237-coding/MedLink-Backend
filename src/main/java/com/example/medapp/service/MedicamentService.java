package com.example.medapp.service;

import com.example.medapp.dto.medicament.MedicamentRequest;
import com.example.medapp.model.Medicament;
import com.example.medapp.model.Pharmacie;
import com.example.medapp.repository.MedicamentRepository;
import com.example.medapp.repository.PharmacieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;
    private final PharmacieRepository pharmacieRepository;

    public MedicamentService(MedicamentRepository medicamentRepository, PharmacieRepository pharmacieRepository) {
        this.medicamentRepository = medicamentRepository;
        this.pharmacieRepository = pharmacieRepository;
    }

    /**
     * Création d'un médicament depuis un DTO.
     */
    public Medicament create(MedicamentRequest req) {
        Pharmacie ph = pharmacieRepository.findById(req.getPharmacieId()).orElseThrow(() -> new IllegalArgumentException("Pharmacie introuvable"));
        Medicament m = new Medicament();
        m.setNom(req.getNom());
        m.setDescription(req.getDescription());
        m.setPharmacie(ph);
        if (req.getDisponible() != null) m.setDisponible(req.getDisponible());
        return medicamentRepository.save(m);
    }

    /**
     * Mise à jour d'un médicament depuis un DTO.
     */
    public Medicament update(Long id, MedicamentRequest req) {
        Medicament m = medicamentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Médicament introuvable"));
        m.setNom(req.getNom());
        m.setDescription(req.getDescription());
        if (req.getDisponible() != null) m.setDisponible(req.getDisponible());
        if (req.getPharmacieId() != null) {
            Pharmacie ph = pharmacieRepository.findById(req.getPharmacieId()).orElseThrow(() -> new IllegalArgumentException("Pharmacie introuvable"));
            m.setPharmacie(ph);
        }
        return medicamentRepository.save(m);
    }

    /**
     * Suppression d'un médicament par son ID.
     */
    public void delete(Long id) { medicamentRepository.deleteById(id); }

    public Medicament get(Long id) { return medicamentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Médicament introuvable")); }

    public List<Medicament> findAll() { return medicamentRepository.findAll(); }

    public List<Medicament> searchByName(String q) { return medicamentRepository.findByNomContainingIgnoreCase(q); }

    public List<Medicament> available() { return medicamentRepository.findByDisponibleTrue(); }
}
