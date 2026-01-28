package com.examedia.hospital.service;

import com.examedia.hospital.constant.OperationMessage;
import com.examedia.hospital.entity.Patient;
import com.examedia.hospital.model.PatientDTO;
import com.examedia.hospital.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public PatientDTO getPatientById(Long id){
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

    public List<PatientDTO> getPatientList(String name, String phoneNumber, Pageable pageable){
        Page<Patient> patientPage = patientRepository.searchByNameAndPhoneNumber(name, phoneNumber, pageable);

        Page<PatientDTO> patientDTOPage = patientPage.map(patient -> modelMapper.map(patient, PatientDTO.class));

        return patientDTOPage.stream().toList();
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
