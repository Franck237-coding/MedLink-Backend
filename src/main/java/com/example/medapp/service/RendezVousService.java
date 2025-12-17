package com.example.medapp.service;

import com.example.medapp.dto.rendezvous.RendezVousRequest;
import com.example.medapp.model.Medecin;
import com.example.medapp.model.Patient;
import com.example.medapp.model.RendezVous;
import com.example.medapp.repository.MedecinRepository;
import com.example.medapp.repository.PatientRepository;
import com.example.medapp.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousService {
    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    public RendezVousService(RendezVousRepository rendezVousRepository, PatientRepository patientRepository, MedecinRepository medecinRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }

    /**
     * Création d'un rendez-vous à partir d'un DTO.
     */
    public RendezVous create(RendezVousRequest req) {
        Patient p = patientRepository.findById(req.getPatientId()).orElseThrow(() -> new IllegalArgumentException("Patient introuvable"));
        Medecin m = medecinRepository.findById(req.getMedecinId()).orElseThrow(() -> new IllegalArgumentException("Médecin introuvable"));
        RendezVous rdv = new RendezVous();
        rdv.setPatient(p);
        rdv.setMedecin(m);
        rdv.setDateHeure(req.getDateHeure());
        return rendezVousRepository.save(rdv);
    }

    /**
     * Récupération des rendez-vous d'un patient.
     */
    public List<RendezVous> byPatient(Long patientId) {
        Patient p = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient introuvable"));
        return rendezVousRepository.findByPatient(p);
    }

    public List<RendezVous> byMedecin(Long medecinId) {
        Medecin m = medecinRepository.findById(medecinId).orElseThrow(() -> new IllegalArgumentException("Médecin introuvable"));
        return rendezVousRepository.findByMedecin(m);
    }

    public RendezVous updateStatut(Long id, RendezVous.StatutRendezVous statut) {
        RendezVous rdv = rendezVousRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Rendez-vous introuvable"));
        rdv.setStatut(statut);
        return rendezVousRepository.save(rdv);
    }

    public void delete(Long id) {
        rendezVousRepository.deleteById(id);
    }

    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }
}
