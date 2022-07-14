package com.sehat.SehatApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultationsFormDTO {
    private Long patient_id;
    private Long doctor_id;
    private String diagnosis;
    private String medicines;
    private String prognosis;
}
