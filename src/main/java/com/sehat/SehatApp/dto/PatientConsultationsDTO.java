package com.sehat.SehatApp.dto;


import com.sehat.SehatApp.model.Patient;
import com.sehat.SehatApp.pojos.PatientConsultations;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PatientConsultationsDTO {

    Patient patient;
    List<PatientConsultations> patient_consultations;

}
