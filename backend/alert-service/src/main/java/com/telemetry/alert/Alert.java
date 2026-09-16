package com.telemetry.alert;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "VIN must not be blank")
    @Column(nullable = false)
    private String vin;

    @NotBlank(message = "Alert type must not be blank")
    @Column(nullable = false)
    private String alertType;

    @NotBlank(message = "Alert message must not be blank")
    @Column(nullable = false)
    private String message;

    @NotBlank(message = "Severity must not be blank")
    @Column(nullable = false)
    private String severity;

    @NotNull(message = "Timestamp must not be null")
    @Column(nullable = false)
    private String timestamp;

    public Alert() {
    }

    public Alert(String vin, String alertType, String message,
                 String severity, String timestamp) {
        this.vin = vin;
        this.alertType = alertType;
        this.message = message;
        this.severity = severity;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
