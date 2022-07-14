package com.sehat.SehatApp.controller;


import com.sehat.SehatApp.model.Patient;
import com.sehat.SehatApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/patient")
public class PatientController {


    @Autowired
    PatientService patientService;

    @GetMapping("/id/{patient_id}")
    public ResponseEntity<Optional<Patient>> findByPid(@PathVariable Long patient_id) {
        return new ResponseEntity<Optional<Patient>>(patientService.findById(patient_id), HttpStatus.OK);
    }

}
