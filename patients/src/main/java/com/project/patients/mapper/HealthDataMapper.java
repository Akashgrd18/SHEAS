package com.project.patients.mapper;

import com.project.patients.dto.HealthDataDto;
import com.project.patients.entity.HealthData;

public class HealthDataMapper {
   public static HealthDataDto mapToHealthDataDto(HealthData healthData, HealthDataDto healthDataDto){
       healthDataDto.setPatientId(healthData.getPatientId());
       healthDataDto.setHeartbeat(healthData.getHeartbeat());
       healthDataDto.setBp(healthData.getBp());

       return  healthDataDto;
   }

   public static HealthData mapToHealthData(HealthDataDto healthDataDto, HealthData healthData){
       healthData.setPatientId(healthDataDto.getPatientId());
       healthData.setHeartbeat(healthDataDto.getHeartbeat());
       healthData.setBp(healthDataDto.getBp());

       return  healthData;
   }
}
