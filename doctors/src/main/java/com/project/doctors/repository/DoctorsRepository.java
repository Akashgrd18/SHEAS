package com.project.doctors.repository;

import com.project.doctors.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
    Optional<Doctors> findByMobileNumber(String mobileNumber);
}
