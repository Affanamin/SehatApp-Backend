package com.sehat.SehatApp.service;


import com.sehat.SehatApp.dto.DoctorConsultationsDTO;
import com.sehat.SehatApp.model.Consultation;
import com.sehat.SehatApp.model.Doctor;
import com.sehat.SehatApp.model.Patient;
import com.sehat.SehatApp.pojos.DoctorConsultations;
import com.sehat.SehatApp.repository.ConsultationRepository;
import com.sehat.SehatApp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    public DoctorConsultationsDTO findByIdGroupByPatient(Long doctor_id) {

        Optional<Doctor> d = doctorRepository.findById(doctor_id);
        Doctor doctor = d.isPresent() ? d.get() : null;

        List<Consultation> consultations = consultationRepository.findByDoctorId(doctor_id);
        HashMap<Patient, List<Consultation>> map = new HashMap<>();
        consultations.stream().forEach(c -> {
            Patient patient = c.getPatient();
            if (map.containsKey(patient)) {
                List<Consultation> listOfConsultations = map.get(patient);
                listOfConsultations.add(c);
                map.put(patient, listOfConsultations);
            } else {
                List<Consultation> listOfConsultations = new ArrayList<Consultation>();
                listOfConsultations.add(c);
                map.putIfAbsent(patient, listOfConsultations);
            }
        });

        List<DoctorConsultations> listOfDoctorConsultations = new ArrayList<>();
        for (Map.Entry<Patient, List<Consultation>> entry: map.entrySet()) {
            DoctorConsultations doctorConsultations = new DoctorConsultations(entry.getKey(), entry.getValue());
            listOfDoctorConsultations.add(doctorConsultations);
        }
        return new DoctorConsultationsDTO(doctor, listOfDoctorConsultations);
    }



    public List<Doctor> findByCityAndSpeciality(String city, String speciality) {
        return doctorRepository.findByCityAndSpecialityIgnoreCasePartialMatch(city.trim().toLowerCase(), speciality.trim().toLowerCase());
    }

    public Optional<Doctor> findById(Long doctor_id) {
        return doctorRepository.findById(doctor_id);
    }


    public Optional<Doctor> findByEmailAndPassword(String email, String password) {
        return doctorRepository.findByEmailAndPassword(email, password);
    }


    public String registerDoctor(Doctor doctor) {
        if (doctorRepository.findById(doctor.getDid()).isPresent()) {
            return "Doctor already exists";
        } else {
            try {
                doctorRepository.save(doctor);
                return "Doctor registered successfully";
            } catch (Exception e) {
                return "Doctor registration failed";
            }
        }
    }
}
