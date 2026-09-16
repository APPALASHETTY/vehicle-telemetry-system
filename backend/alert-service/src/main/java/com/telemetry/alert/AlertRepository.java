package com.telemetry.alert;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    boolean existsByVinAndAlertTypeAndTimestamp(
            String vin,
            String alertType,
            String timestamp
    );
}
