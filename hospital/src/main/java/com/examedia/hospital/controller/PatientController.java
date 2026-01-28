package com.examedia.hospital.controller;

import com.examedia.hospital.model.PatientDTO;
import com.examedia.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
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

    @GetMapping
    public List<PatientDTO> getListPatient(@RequestParam (defaultValue = "") String name,
                                           @RequestParam (defaultValue = "") String phoneNumber,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10")  int size){
        Pageable pageable = PageRequest.of(page, size);
        return patientService.getPatientList(name, phoneNumber, pageable);
    }
}
