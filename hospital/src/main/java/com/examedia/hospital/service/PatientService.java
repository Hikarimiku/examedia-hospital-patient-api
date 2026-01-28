package com.examedia.hospital.service;

import com.examedia.hospital.constant.OperationMessage;
import com.examedia.hospital.entity.Patient;
import com.examedia.hospital.model.PatientDTO;
import com.examedia.hospital.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public PatientDTO getPatient(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  OperationMessage.PATIENT_NOT_FOUND));

        return modelMapper.map(patient, PatientDTO.class);
    }

    public PatientDTO createPatient(PatientDTO patientDTO){
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patient.setIsActive(true);
        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  OperationMessage.PATIENT_NOT_FOUND));

        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setEmergencyContact(patientDTO.getEmergencyContact());
        patient.setAddress(patientDTO.getAddress());
        patient.setBloodType(patientDTO.getBloodType());

        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    public String deletePatient(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, OperationMessage.PATIENT_NOT_FOUND));

        patient.setIsActive(false);
        patientRepository.save(patient);
        return OperationMessage.SUCCESS_DELETE_PATIENT;
    }
}
