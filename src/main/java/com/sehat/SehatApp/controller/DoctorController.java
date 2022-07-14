package com.sehat.SehatApp.controller;


import com.sehat.SehatApp.model.Doctor;
import com.sehat.SehatApp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Doctor>> findByCity(@PathVariable String city, @RequestParam String speciality) {
        return new ResponseEntity<List<Doctor>>(doctorService.findByCityAndSpeciality(city, speciality), HttpStatus.OK);
    }

    @GetMapping("/id/{doctor_id}")
    public ResponseEntity<Optional<Doctor>> findByDid(@PathVariable Long doctor_id){
        return new ResponseEntity<Optional<Doctor>>(doctorService.findById(doctor_id), HttpStatus.OK);
    }



}
