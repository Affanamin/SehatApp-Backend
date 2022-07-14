package com.sehat.SehatApp.repository;


import com.sehat.SehatApp.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    @Query("select c from Consultation c where c.patient.pid = ?1")
    List<Consultation> findByPatientId(Long patient_id);


    @Query("select c from Consultation c where c.doctor.did = ?1")
    List<Consultation> findByDoctorId(Long doctor_id);
}
