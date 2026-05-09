package com.example.alertService.service.impl;

import com.example.alertService.dto.AlertDto;
import com.example.alertService.entity.Alert;
import com.example.alertService.exception.ResourceNotFoundException;
import com.example.alertService.mapper.AlertMapper;
import com.example.alertService.repository.AlertRepository;
import com.example.alertService.service.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService implements IAlertService {

    @Autowired
    private AlertRepository alertRepository;


    @Override
    public AlertDto createAlert(AlertDto alertDto) {

        Alert alert = AlertMapper.mapToAlert(alertDto, new Alert());
        alertRepository.save(alert);
        return alertDto;
    }

    @Override
    public List<AlertDto> getAlertHistory(Long patientId) {

        List<Alert> alerts = alertRepository.findAllByPatientId(patientId);

        if(alerts.isEmpty()){
            throw new ResourceNotFoundException("Alert", "patientId", patientId.toString());
        }

        List<AlertDto> alertDtos = new ArrayList<>();
        for (Alert alert : alerts) {
            AlertDto alertDto = AlertMapper.mapToAlertDto(alert, new AlertDto());
            alertDtos.add(alertDto);
        }
        return alertDtos;
    }
}
