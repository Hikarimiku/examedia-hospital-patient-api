package com.examedia.hospital.controller;

import com.examedia.hospital.model.PatientDTO;
import com.examedia.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable Long id){
        return patientService.getPatient(id);
    }

    @PostMapping
    public PatientDTO createPatient(@Valid @RequestBody PatientDTO patientDTO){
        return patientService.createPatient(patientDTO);
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO){
        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id){
        return patientService.deletePatient(id);
    }
}
