package com.sehat.SehatApp.repository;

import com.sehat.SehatApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByEmailAndPassword(String email, String password);
}
