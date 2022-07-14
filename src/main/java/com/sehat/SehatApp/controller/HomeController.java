package com.sehat.SehatApp.controller;


import com.sehat.SehatApp.model.Doctor;
import com.sehat.SehatApp.model.Login;
import com.sehat.SehatApp.model.Patient;
import com.sehat.SehatApp.service.DoctorService;
import com.sehat.SehatApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
public class HomeController {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;


    @PostMapping("/register/doctor")
    @ResponseBody
    public String doctorRegistration(@RequestBody Doctor details) {
        Doctor doctor = new Doctor(details.getDid(), details.getName(), details.getAadhar(), details.getAddress(), details.getCity(), details.getMobile(), details.getSpeciality(), new ArrayList<>());
        doctor.setEmail(details.getEmail());
        doctor.setPassword(details.getPassword());
        return doctorService.registerDoctor(doctor);
    }

    @PostMapping("/register/patient")
    @ResponseBody
    public String patientRegistration(@RequestBody Patient details) {

        Patient patient = new Patient(details.getPid(), details.getName(), details.getAadhar(), details.getDob(), details.getAddress(), details.getCity(), details.getMobile(), new ArrayList<>());
        patient.setEmail(details.getEmail());
        patient.setPassword(details.getPassword());
        return patientService.registerPatient(patient);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login details) {
        String email = details.getEmail();
        String password = details.getPassword();
        //log.info(email + " " + password);
        Optional<Patient> patient = patientService.findByEmailAndPassword(email, password);
        Optional<Doctor> doctor = doctorService.findByEmailAndPassword(email, password);
        if (patient.isPresent()) {
            return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
        }
        if (doctor.isPresent()) {
            return new ResponseEntity<Doctor>(doctor.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }



    }
