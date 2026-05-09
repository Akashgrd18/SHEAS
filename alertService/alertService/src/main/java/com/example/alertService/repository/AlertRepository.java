package com.example.alertService.repository;


import com.example.alertService.entity.Alert;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    /**
     * Fetches all alerts for a given patient ID.
     *
     * @EntityGraph is used here to solve the N+1 query problem.
     *
     * WITHOUT @EntityGraph (N+1 problem):
     *   Query 1: SELECT * FROM alert WHERE patient_id = ?       → returns 10 alerts
     *   Query 2: SELECT * FROM alert_messages WHERE alert_id = 1
     *   Query 3: SELECT * FROM alert_messages WHERE alert_id = 2
     *   ... 10 more queries = 11 total queries ❌
     *
     * WITH @EntityGraph (Fixed):
     *   Query 1: SELECT a.*, m.* FROM alert a
     *            LEFT JOIN alert_messages m ON a.id = m.alert_id
     *            WHERE a.patient_id = ?
     *   = 1 single query with JOIN ✅
     */
    @EntityGraph(attributePaths = {"alertMessage"})
    List<Alert> findAllByPatientId(Long patientId);
}
