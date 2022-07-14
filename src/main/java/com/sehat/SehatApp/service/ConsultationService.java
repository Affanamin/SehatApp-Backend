package com.sehat.SehatApp.service;

import com.sehat.SehatApp.model.Consultation;
import com.sehat.SehatApp.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

    public List<Consultation> findByPatientId(Long patient_id) {
        return consultationRepository.findByPatientId(patient_id);
    }

}
