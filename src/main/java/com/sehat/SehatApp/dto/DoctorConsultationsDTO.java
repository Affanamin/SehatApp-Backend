package com.sehat.SehatApp.dto;

import com.sehat.SehatApp.model.Doctor;
import com.sehat.SehatApp.pojos.DoctorConsultations;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoctorConsultationsDTO {

    Doctor doctor;
    List<DoctorConsultations> doctor_consultations;
}
