package com.sehat.SehatApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "consultations")
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Long cid;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    private String prognosis;
    @NotNull
    private String medicines;
    @NotNull
    private String diagnosis;


    @JsonBackReference(value="patient_id")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonBackReference(value="doctor_id")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Consultation(LocalDate date, String prognosis, String medicines, String diagnosis, Patient patient,
                        Doctor doctor) {
        super();
        this.date = date;
        this.prognosis = prognosis;
        this.medicines = medicines;
        this.diagnosis = diagnosis;
        this.patient = patient;
        this.doctor = doctor;
    }

}
