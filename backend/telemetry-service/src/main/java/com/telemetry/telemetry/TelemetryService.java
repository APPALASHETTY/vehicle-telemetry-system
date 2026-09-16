package com.telemetry.telemetry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;
    private final RestClient restClient;

    @Value("${alert.service.url}")
    private String alertServiceUrl;

    @Value("${vehicle.service.url}")
    private String vehicleServiceUrl;

    public TelemetryService(
            TelemetryRepository telemetryRepository,
            RestClient.Builder restClientBuilder) {

        this.telemetryRepository = telemetryRepository;
        this.restClient = restClientBuilder.build();
    }

    public List<Telemetry> getAllTelemetry() {
        return telemetryRepository.findAll();
    }

    public Map<String, Object> createTelemetry(Telemetry telemetry) {

        // Validate vehicle before saving telemetry
        try {

            restClient.get()
                    .uri(vehicleServiceUrl + "/vehicles/" + telemetry.getVin())
                    .retrieve()
                    .toBodilessEntity();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Vehicle not found for VIN=" + telemetry.getVin()
            );
        }

        // Save telemetry
        Telemetry savedTelemetry = telemetryRepository.save(telemetry);

        // Create response
        Map<String, Object> response = new HashMap<>();
        response.put("telemetry", savedTelemetry);

        // Store all alerts generated for this telemetry
        List<AlertResponse> alerts = new ArrayList<>();

        // HIGH_SPEED rule
        if (savedTelemetry.getSpeed() > 120) {

            AlertRequest alertRequest = new AlertRequest();

            alertRequest.setVin(savedTelemetry.getVin());
            alertRequest.setAlertType("HIGH_SPEED");
            alertRequest.setMessage(
                    "Vehicle speed exceeded the allowed limit"
            );
            alertRequest.setSeverity("HIGH");
            alertRequest.setTimestamp(savedTelemetry.getTimestamp());

            AlertResponse alertResponse = restClient.post()
                    .uri(alertServiceUrl + "/alerts")
                    .body(alertRequest)
                    .retrieve()
                    .body(AlertResponse.class);

            alerts.add(alertResponse);
        }

        // HIGH_TEMPERATURE rule
        if (savedTelemetry.getEngineTemperature() > 100) {

            AlertRequest alertRequest = new AlertRequest();

            alertRequest.setVin(savedTelemetry.getVin());
            alertRequest.setAlertType("HIGH_TEMPERATURE");
            alertRequest.setMessage(
                    "Vehicle engine temperature exceeded the allowed limit"
            );
            alertRequest.setSeverity("HIGH");
            alertRequest.setTimestamp(savedTelemetry.getTimestamp());

            AlertResponse alertResponse = restClient.post()
                    .uri(alertServiceUrl + "/alerts")
                    .body(alertRequest)
                    .retrieve()
                    .body(AlertResponse.class);

            alerts.add(alertResponse);
        }

        // Add alerts to response if any were generated
        if (!alerts.isEmpty()) {
            response.put("alerts", alerts);
        }

        return response;
    }

    public Telemetry getTelemetryById(Long id) {

        return telemetryRepository.findById(id)
                .orElseThrow(() ->
                        new TelemetryNotFoundException(
                                "Telemetry not found"
                        ));
    }

    public Telemetry updateTelemetry(
            Long id,
            Telemetry updatedTelemetry) {

        Telemetry existingTelemetry =
                telemetryRepository.findById(id)
                        .orElseThrow(() ->
                                new TelemetryNotFoundException(
                                        "Telemetry not found"
                                ));

        existingTelemetry.setVin(updatedTelemetry.getVin());
        existingTelemetry.setSpeed(updatedTelemetry.getSpeed());

        existingTelemetry.setEngineTemperature(
                updatedTelemetry.getEngineTemperature()
        );

        existingTelemetry.setLatitude(
                updatedTelemetry.getLatitude()
        );

        existingTelemetry.setLongitude(
                updatedTelemetry.getLongitude()
        );

        existingTelemetry.setTimestamp(
                updatedTelemetry.getTimestamp()
        );

        return telemetryRepository.save(existingTelemetry);
    }

    public void deleteTelemetry(Long id) {

        Telemetry telemetry =
                telemetryRepository.findById(id)
                        .orElseThrow(() ->
                                new TelemetryNotFoundException(
                                        "Telemetry not found"
                                ));

        telemetryRepository.delete(telemetry);
    }
}