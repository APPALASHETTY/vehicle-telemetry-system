package com.telemetry.alert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final RestClient restClient;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    public AlertService(
            AlertRepository alertRepository,
            RestClient.Builder restClientBuilder) {

        this.alertRepository = alertRepository;
        this.restClient = restClientBuilder.build();
    }

    // Get all alerts
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Create an alert
    public Alert createAlert(Alert alert) {

        // Check for duplicate alert
        boolean duplicateExists =
                alertRepository.existsByVinAndAlertTypeAndTimestamp(
                        alert.getVin(),
                        alert.getAlertType(),
                        alert.getTimestamp()
                );

        if (duplicateExists) {

            System.out.println(
                    "Duplicate alert ignored: VIN="
                            + alert.getVin()
                            + " | AlertType="
                            + alert.getAlertType()
                            + " | Timestamp="
                            + alert.getTimestamp()
            );

            return alertRepository.findAll()
                    .stream()
                    .filter(existingAlert ->
                            existingAlert.getVin().equals(alert.getVin())
                                    && existingAlert.getAlertType()
                                    .equals(alert.getAlertType())
                                    && existingAlert.getTimestamp()
                                    .equals(alert.getTimestamp())
                    )
                    .findFirst()
                    .orElse(null);
        }

        // Save new alert
        Alert savedAlert = alertRepository.save(alert);

        System.out.println(
                "ALERT SAVED: " + savedAlert.getId()
        );

        // Create notification request WITHOUT alert ID
        Map<String, Object> notificationRequest = new HashMap<>();

        notificationRequest.put("vin", savedAlert.getVin());
        notificationRequest.put("alertType", savedAlert.getAlertType());
        notificationRequest.put("message", savedAlert.getMessage());
        notificationRequest.put("severity", savedAlert.getSeverity());
        notificationRequest.put("timestamp", savedAlert.getTimestamp());

        // Send notification to Notification Service
        try {

            System.out.println(
                    "Sending notification to: "
                            + notificationServiceUrl
                            + "/notifications"
            );

            restClient.post()
                    .uri(notificationServiceUrl + "/notifications")
                    .body(notificationRequest)
                    .retrieve()
                    .toBodilessEntity();

            System.out.println(
                    "Notification sent successfully for alert: "
                            + savedAlert.getAlertType()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to send notification for alert: "
                            + savedAlert.getAlertType()
            );

            e.printStackTrace();
        }

        return savedAlert;
    }

    // Get alert by ID
    public Alert getAlertById(Long id) {

        return alertRepository.findById(id)
                .orElseThrow(() ->
                        new AlertNotFoundException(
                                "Alert not found with id: " + id
                        ));
    }

    // Update alert
    public Alert updateAlert(Long id, Alert updatedAlert) {

        Alert existingAlert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new AlertNotFoundException(
                                "Alert not found with id: " + id
                        ));

        existingAlert.setVin(updatedAlert.getVin());
        existingAlert.setAlertType(updatedAlert.getAlertType());
        existingAlert.setMessage(updatedAlert.getMessage());
        existingAlert.setSeverity(updatedAlert.getSeverity());
        existingAlert.setTimestamp(updatedAlert.getTimestamp());

        return alertRepository.save(existingAlert);
    }

    // Delete alert
    public void deleteAlert(Long id) {

        if (!alertRepository.existsById(id)) {

            throw new AlertNotFoundException(
                    "Alert not found with id: " + id
            );
        }

        alertRepository.deleteById(id);
    }
}