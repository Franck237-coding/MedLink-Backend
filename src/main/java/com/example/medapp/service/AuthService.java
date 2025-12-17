package com.example.medapp.service;

import com.example.medapp.dto.auth.SignupRequest;
import com.example.medapp.model.*;
import com.example.medapp.repository.MedecinRepository;
import com.example.medapp.repository.PatientRepository;
import com.example.medapp.repository.PharmacieRepository;
import com.example.medapp.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final PharmacieRepository pharmacieRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UtilisateurRepository utilisateurRepository,
                       PatientRepository patientRepository,
                       MedecinRepository medecinRepository,
                       PharmacieRepository pharmacieRepository,
                       PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.pharmacieRepository = pharmacieRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Inscription d'un utilisateur selon le rôle fourni dans le DTO.
     *
     * @param r objet SignupRequest contenant les informations de l'utilisateur
     * @return l'utilisateur créé
     */
    @Transactional
    public Utilisateur signup(SignupRequest r) {
        if (utilisateurRepository.existsByEmail(r.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        String hashed = passwordEncoder.encode(r.getPassword());
        Role role = r.getRole();
        switch (role) {
            case PATIENT -> {
                Patient p = new Patient();
                p.setEmail(r.getEmail());
                p.setPassword(hashed);
                p.setRole(Role.PATIENT);
                p.setNom(r.getNom());
                p.setTelephone(r.getTelephone());
                if (r.getDateNaissance() != null && !r.getDateNaissance().isBlank()) {
                    p.setDateNaissance(LocalDate.parse(r.getDateNaissance()));
                }
                p.setAdresse(r.getAdresse());
                p.setSexe(r.getSexe());
                return patientRepository.save(p);
            }
            case MEDECIN -> {
                Medecin m = new Medecin();
                m.setEmail(r.getEmail());
                m.setPassword(hashed);
                m.setRole(Role.MEDECIN);
                m.setNom(r.getNom());
                m.setTelephone(r.getTelephone());
                m.setSpecialite(r.getSpecialite());
                m.setCabinet(r.getCabinet());
                m.setNumeroOrdre(r.getNumeroOrdre());
                return medecinRepository.save(m);
            }
            case PHARMACIE -> {
                Pharmacie ph = new Pharmacie();
                ph.setEmail(r.getEmail());
                ph.setPassword(hashed);
                ph.setRole(Role.PHARMACIE);
                ph.setNom(r.getNom());
                ph.setTelephone(r.getTelephone());
                ph.setNomCommercial(r.getNomCommercial());
                ph.setRegistreCommerce(r.getRegistreCommerce());
                ph.setAdresse(r.getAdresse());
                return pharmacieRepository.save(ph);
            }
            case ADMIN -> {
                // Basic admin as Utilisateur is abstract; use Medecin as admin surrogate or create dedicated Admin entity in future
                Medecin admin = new Medecin();
                admin.setEmail(r.getEmail());
                admin.setPassword(hashed);
                admin.setRole(Role.ADMIN);
                admin.setNom(r.getNom());
                admin.setTelephone(r.getTelephone());
                return medecinRepository.save(admin);
            }
            default -> throw new IllegalArgumentException("Rôle invalide");
        }
    }
}
