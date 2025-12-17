package com.example.medapp.service;

import com.example.medapp.model.*;
import com.example.medapp.repository.MedecinRepository;
import com.example.medapp.repository.PatientRepository;
import com.example.medapp.repository.PharmacieRepository;
import com.example.medapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UtilisateurRepository utilisateurRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final PharmacieRepository pharmacieRepository;

    public UserService(UtilisateurRepository utilisateurRepository,
                       PatientRepository patientRepository,
                       MedecinRepository medecinRepository,
                       PharmacieRepository pharmacieRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.pharmacieRepository = pharmacieRepository;
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Transactional
    public Utilisateur updateProfile(Utilisateur existing, Utilisateur patch) {
        existing.setNom(patch.getNom() != null ? patch.getNom() : existing.getNom());
        existing.setTelephone(patch.getTelephone() != null ? patch.getTelephone() : existing.getTelephone());
        if (existing instanceof Patient p && patch instanceof Patient pp) {
            p.setAdresse(pp.getAdresse() != null ? pp.getAdresse() : p.getAdresse());
            p.setSexe(pp.getSexe() != null ? pp.getSexe() : p.getSexe());
            p.setDateNaissance(pp.getDateNaissance() != null ? pp.getDateNaissance() : p.getDateNaissance());
            return patientRepository.save(p);
        }
        if (existing instanceof Medecin m && patch instanceof Medecin mm) {
            m.setSpecialite(mm.getSpecialite() != null ? mm.getSpecialite() : m.getSpecialite());
            m.setCabinet(mm.getCabinet() != null ? mm.getCabinet() : m.getCabinet());
            m.setNumeroOrdre(mm.getNumeroOrdre() != null ? mm.getNumeroOrdre() : m.getNumeroOrdre());
            return medecinRepository.save(m);
        }
        if (existing instanceof Pharmacie ph && patch instanceof Pharmacie phPatch) {
            ph.setNomCommercial(phPatch.getNomCommercial() != null ? phPatch.getNomCommercial() : ph.getNomCommercial());
            ph.setAdresse(phPatch.getAdresse() != null ? phPatch.getAdresse() : ph.getAdresse());
            ph.setRegistreCommerce(phPatch.getRegistreCommerce() != null ? phPatch.getRegistreCommerce() : ph.getRegistreCommerce());
            return pharmacieRepository.save(ph);
        }
        return utilisateurRepository.save(existing);
    }
}
