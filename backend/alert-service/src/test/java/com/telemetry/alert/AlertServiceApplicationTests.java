
package com.telemetry.alert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlertServiceApplicationTests {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    void shouldInsertAndReadAlert() {

        String testVin = "TEST-" + UUID.randomUUID();

        Alert alert = new Alert(
                testVin,
                "ENGINE_TEMPERATURE",
                "Engine temperature is high",
                "HIGH",
                "2026-11-16T05:00:00"
        );

        Alert savedAlert = alertRepository.saveAndFlush(alert);

        assertNotNull(savedAlert.getId());

        Alert retrievedAlert = alertRepository
                .findById(savedAlert.getId())
                .orElseThrow();

        assertEquals(testVin, retrievedAlert.getVin());
        assertEquals("ENGINE_TEMPERATURE", retrievedAlert.getAlertType());
        assertEquals("Engine temperature is high", retrievedAlert.getMessage());
        assertEquals("HIGH", retrievedAlert.getSeverity());

        alertRepository.deleteById(savedAlert.getId());
        alertRepository.flush();
    }
}
