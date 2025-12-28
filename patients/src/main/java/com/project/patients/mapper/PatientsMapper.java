package com.project.patients.mapper;

import com.project.patients.dto.PatientDetailsDto;
import com.project.patients.dto.PatientsDto;
import com.project.patients.entity.Patients;

public class PatientsMapper {

    public  static PatientsDto mapToPatientsDto(Patients patients, PatientsDto patientsDto){
      patientsDto.setName(patients.getName());
      patientsDto.setAge(patients.getAge());
      patientsDto.setMobileNumber(patients.getMobileNumber());
      patientsDto.setDoctor_id(patients.getDoctor_id());
      return  patientsDto;
    }

    public  static PatientDetailsDto mapToPatientDetailsDto(Patients patients, PatientDetailsDto patientDetailsDto){
        patientDetailsDto.setName(patients.getName());
        patientDetailsDto.setAge(patients.getAge());
        patientDetailsDto.setMobileNumber(patients.getMobileNumber());
        patientDetailsDto.setDoctor_id(patients.getDoctor_id());
        return  patientDetailsDto;
    }



    public  static Patients mapToPatients(PatientsDto patientsDto, Patients patients){
        patients.setName(patientsDto.getName());
        patients.setAge(patientsDto.getAge());
        patients.setMobileNumber(patientsDto.getMobileNumber());
        patients.setDoctor_id(patientsDto.getDoctor_id());
        return patients;
    }


}
