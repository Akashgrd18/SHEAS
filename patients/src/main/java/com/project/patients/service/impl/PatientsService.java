package com.project.patients.service.impl;


import com.project.patients.dto.PatientsDto;
import com.project.patients.entity.Patients;
import com.project.patients.exception.PatientAlreadyExistsException;
import com.project.patients.exception.ResourceNotFoundException;
import com.project.patients.mapper.PatientsMapper;
import com.project.patients.repository.PatientsRepository;
import com.project.patients.service.IPatientsService;
import com.project.patients.service.client.DoctorsFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientsService implements IPatientsService {

    private static final Logger log = LoggerFactory.getLogger(PatientsService.class);
    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    DoctorsFeignClient doctorsFeignClient;

    @Override
    @Transactional
    public void createPatient(PatientsDto patientsDto) {
        log.info("Creating patient with mobile number :{}", patientsDto.getMobileNumber());
        Patients patients = PatientsMapper.mapToPatients(patientsDto, new Patients());
        Optional<Patients> optionalPatients =  patientsRepository.findByMobileNumber(patientsDto.getMobileNumber());
        if(optionalPatients.isPresent()){
            log.error("Patient already present with given mobile number :{}",patientsDto.getMobileNumber());
            throw new PatientAlreadyExistsException("Patient already present with given mobile number" + patientsDto.getMobileNumber());
        }
        patientsRepository.save(patients);
        if(patients.getDoctor_id()!=null) {
            try {

                doctorsFeignClient.updateDoctor(patients.getDoctor_id(), patients.getId());
            } catch (Exception exception) {

                log.error("Doctor service failed to return doctor with id :{}", patientsDto.getDoctor_id());
                throw new RuntimeException("Doctor Service Failed :" + exception.getMessage());
            }
        }
    }

    @Override
    public PatientsDto fetchPatient(String mobileNumber) {
        log.info("Searching patient with mobile number :{}", mobileNumber);
        Patients patients = patientsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> {
                    log.error("Patient is not present with given mobile number :{}", mobileNumber);
                    return  new ResourceNotFoundException("Patient", "mobileNumber", mobileNumber);

                });

        PatientsDto patientsDto = PatientsMapper.mapToPatientsDto(patients, new PatientsDto());

        return patientsDto;
    }

    @Override
    public boolean updateAccount(PatientsDto patientsDto) {
        Patients patients = patientsRepository.findByMobileNumber(patientsDto.getMobileNumber())
                .orElseThrow(() -> {
                    log.error("Patient is not present to update with given mobile number :{}", patientsDto.getMobileNumber());
                    return new ResourceNotFoundException("Patient", "MobileNumber", patientsDto.getMobileNumber());
                });

        PatientsMapper.mapToPatients(patientsDto,patients);
        patientsRepository.save(patients);
        return true;

    }

    @Override
    public boolean deletePatient(String mobileNumber) {
        Patients patients = patientsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> {
                    log.error("Deletion failed as patient is not    present with mobile Number :{}", mobileNumber);
                    return new ResourceNotFoundException("Patient", "MobileNumber", mobileNumber);
                });

        patientsRepository.deleteById(patients.getId());
        return true;
    }

    @Override
    public PatientsDto fetchById(Long id) {
        Patients patients = patientsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "mobileNumber", "Not found"));

        log.info("Fetching the patient with id: {} for alert service ", id);
        PatientsDto patientsDto = PatientsMapper.mapToPatientsDto(patients, new PatientsDto());

        return patientsDto;
    }

    @Override
    public List<PatientsDto> getPatientsById(List<Long> patientIds) {
        log.info("Fetching all the patients with ids: {} for doctor details ", patientIds);
        List<Patients> patientsList = patientsRepository.findAllById(patientIds);
        List<PatientsDto> patientsDtos = new ArrayList<>();

        for (Patients patient : patientsList) {
            PatientsDto dto = new PatientsDto(patient);
            patientsDtos.add(dto);
        }

        return patientsDtos;

    }

}


