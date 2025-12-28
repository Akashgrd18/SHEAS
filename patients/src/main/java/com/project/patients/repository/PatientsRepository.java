package com.project.patients.repository;

import com.project.patients.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {


    Optional<Patients> findByMobileNumber(String mobileNumber);
}
