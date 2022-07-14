package com.sehat.SehatApp.model;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient extends Login{

    @Id
    @Column(name = "patient_id", unique = true)
    private Long pid;
    private String name;
    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long aadhar;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String address;
    private String city;
    @Column(unique = true)
    private Long mobile;

    @JsonManagedReference(value="patient_id")
    @OneToMany(mappedBy = "cid")
    private List<Consultation> consultations = new ArrayList<>();

    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }


    public Patient(String email,String password,Long pid, String name, Long aadhar, LocalDate dob, String address, String city, Long mobile) {
        super(email,password);
        this.pid = pid;
        this.name = name;
        this.aadhar = aadhar;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.mobile = mobile;
    }

}
