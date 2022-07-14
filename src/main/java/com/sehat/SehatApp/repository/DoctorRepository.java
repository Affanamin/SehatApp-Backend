package com.sehat.SehatApp.repository;


import com.sehat.SehatApp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    @Query("select d from Doctor d where lower(d.city) like %?1% and lower(d.speciality) like %?2%")
    List<Doctor> findByCityAndSpecialityIgnoreCasePartialMatch(String city, String speciality);

    Optional<Doctor> findByEmailAndPassword(String email, String password);


}
