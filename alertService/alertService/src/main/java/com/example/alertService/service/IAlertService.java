package com.example.alertService.service;

import com.example.alertService.dto.AlertDto;

import java.util.List;

public interface IAlertService {

    AlertDto createAlert(AlertDto alertDto);

    List<AlertDto> getAlertHistory(Long patientId);
}
