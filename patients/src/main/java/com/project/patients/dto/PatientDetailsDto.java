package com.project.patients.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Schema(
        name = "Patient Details With Doctors",
        description = "Schema to hold patient details along with associated doctors"
)
@Data
@Getter
@Setter
public class PatientDetailsDto {
    private String name;
    private int age;
    private String mobileNumber;
    private Long doctor_id;

    private DoctorsDto doctorsDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public DoctorsDto getDoctorsDto() {
        return doctorsDto;
    }

    public void setDoctorsDto(DoctorsDto doctorsDto) {
        this.doctorsDto = doctorsDto;
    }
}
