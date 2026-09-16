package com.telemetry.alert;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@CrossOrigin(origins = "http://localhost:3000")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // GET /alerts
    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    // POST /alerts
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alert createAlert(@Valid @RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    // GET /alerts/{id}
    @GetMapping("/{id}")
    public Alert getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id);
    }

    // PUT /alerts/{id}
    @PutMapping("/{id}")
    public Alert updateAlert(
            @PathVariable Long id,
            @Valid @RequestBody Alert alert) {

        return alertService.updateAlert(id, alert);
    }

    // DELETE /alerts/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
    }
}