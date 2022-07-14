package com.sehat.SehatApp.pojos;


import com.sehat.SehatApp.model.Consultation;
import com.sehat.SehatApp.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientConsultations {

    Doctor doctor;
    List<Consultation> consultations;
}
