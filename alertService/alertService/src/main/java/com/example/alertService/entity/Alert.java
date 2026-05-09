package com.example.alertService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;

    @ElementCollection
    @CollectionTable(name = "alert_messages", joinColumns = @JoinColumn(name = "alert_id"))
    @Column(name = "message")
    private List<String> alertMessage;

}
