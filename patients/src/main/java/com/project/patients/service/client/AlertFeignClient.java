package com.project.patients.service.client;

import com.project.patients.dto.AlertDto;

import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("alertService")

public interface AlertFeignClient {

        @PostMapping(value = "/create", consumes = "application/json")
        public AlertDto createAlert(@RequestBody AlertDto alertDto);
    }


