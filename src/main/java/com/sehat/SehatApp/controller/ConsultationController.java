package com.sehat.SehatApp.controller;


import com.sehat.SehatApp.dto.ConsultationsFormDTO;
import com.sehat.SehatApp.dto.DoctorConsultationsDTO;
import com.sehat.SehatApp.dto.PatientConsultationsDTO;
import com.sehat.SehatApp.model.Consultation;
import com.sehat.SehatApp.model.Doctor;
import com.sehat.SehatApp.model.Patient;
import com.sehat.SehatApp.repository.ConsultationRepository;
import com.sehat.SehatApp.repository.DoctorRepository;
import com.sehat.SehatApp.repository.PatientRepository;
import com.sehat.SehatApp.service.DoctorService;
import com.sehat.SehatApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/consultations")
public class ConsultationController {


    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;


    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    @GetMapping("/patient/{patient_id}")
    public ResponseEntity<?> patientHistory(@PathVariable Long patient_id) {
        PatientConsultationsDTO patientConsultationsDTO = patientService.findByIdGroupByDoctor(patient_id);
        if (patientConsultationsDTO.getPatient()!=null)
            return new ResponseEntity<PatientConsultationsDTO>(patientConsultationsDTO, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/doctor/{doctor_id}")
    public ResponseEntity<?> doctorHistory(@PathVariable Long doctor_id) {
        DoctorConsultationsDTO doctorConsultationsDTO = doctorService.findByIdGroupByPatient(doctor_id);
        if (doctorConsultationsDTO.getDoctor()!=null)
            return new ResponseEntity<DoctorConsultationsDTO>(doctorConsultationsDTO, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/form")
    @ResponseBody
    public String consultPatient(@RequestBody ConsultationsFormDTO consultationForm) {
        Long patient_id = consultationForm.getPatient_id();
        Long doctor_id = consultationForm.getDoctor_id();
        String diagnosis = consultationForm.getDiagnosis();
        String medicines = consultationForm.getMedicines();
        String prognosis = consultationForm.getPrognosis();

        Optional<Patient> patient = patientService.findById(patient_id);
        Optional<Doctor> doctor = doctorService.findById(doctor_id);
        if (patient.isPresent() && doctor.isPresent()) {
            Consultation consultation = new Consultation(LocalDate.now(), prognosis, medicines, diagnosis, patient.get(), doctor.get());
            consultationRepository.save(consultation);
            return "Patient consulted successfully";
        }
        return "Patient consultation failed";
    }




}
