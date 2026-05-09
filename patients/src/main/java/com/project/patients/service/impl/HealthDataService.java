package com.project.patients.service.impl;


import com.project.patients.dto.*;
import com.project.patients.entity.HealthData;
import com.project.patients.entity.Patients;
import com.project.patients.exception.ResourceNotFoundException;
import com.project.patients.mapper.HealthDataMapper;

import com.project.patients.mapper.PatientsMapper;
import com.project.patients.repository.HealthDataRepository;
import com.project.patients.repository.PatientsRepository;
import com.project.patients.service.IHealthDataService;
import com.project.patients.service.client.AlertFeignClient;
import com.project.patients.service.client.DoctorsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HealthDataService implements IHealthDataService {

    @Autowired
    private HealthDataRepository healthDataRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private AlertFeignClient alertFeignClient;

    @Autowired
    private DoctorsFeignClient doctorsFeignClient;




    @Override
    public void createData(HealthDataDto healthDataDto) {
        HealthData healthData = HealthDataMapper.mapToHealthData(healthDataDto, new HealthData());
        Optional<Patients> patients = patientsRepository.findById(healthDataDto.getPatientId());
        if(patients.isEmpty()){
            throw new ResourceNotFoundException("Patients", "id", healthDataDto.getPatientId().toString());
        }
        healthDataRepository.save(healthData);
    }

    @Override
    public PatientDetailsWithAlertAndDoctor checkData(HealthDataDto healthDataDto) {

        boolean isAlertCreated = false;
        AlertDto alertDto = new AlertDto();
        List<String> msg = new ArrayList<>();

        Patients patients;
        if(healthDataDto.getHeartbeat() < 50 || healthDataDto.getHeartbeat() >120 ){
            isAlertCreated = true;

            alertDto.setPatientId(healthDataDto.getPatientId());
            msg.add("Alert HeartRate is in bad condition: "+healthDataDto.getHeartbeat());


        }
        if(healthDataDto.getBp() < 60 || healthDataDto.getBp()>200){
            isAlertCreated = true;
            alertDto.setPatientId(healthDataDto.getPatientId());
            msg.add("Alert BP is in bad condition :"+healthDataDto.getBp());
        }

        alertDto.setAlertMessage(msg);
        AlertDto alertDto1 = null;
        if(isAlertCreated) {
            alertDto1 = alertFeignClient.createAlert(alertDto);
        }
        patients = patientsRepository.findById(healthDataDto.getPatientId()).orElseThrow(
                ()-> new ResourceNotFoundException("Patient", "health data", "Alert Not valid" )
        );

        PatientsDto patientsDto = PatientsMapper.mapToPatientsDto(patients, new PatientsDto());
        ResponseEntity<DoctorsDto> doctorsDtoResponseEntity;

        try {
            doctorsDtoResponseEntity = doctorsFeignClient.fetchDoctorById(patients.getDoctor_id());
        }catch (Exception e){
            throw new ResourceNotFoundException("Doctor", "patient",patients.getName());
        }

        DoctorsDto doctorsDto = doctorsDtoResponseEntity.getBody();


        PatientDetailsWithAlertAndDoctor patientDetailsWithAlertAndDoctor = new PatientDetailsWithAlertAndDoctor();
        patientDetailsWithAlertAndDoctor.setPatientsDto(patientsDto);
        patientDetailsWithAlertAndDoctor.setDoctorsDto(doctorsDto);
        patientDetailsWithAlertAndDoctor.setAlertDto(alertDto1);
        patientDetailsWithAlertAndDoctor.setStatus(isAlertCreated);

        return patientDetailsWithAlertAndDoctor;
    }
}
