package com.telemetry.telemetry;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/telemetry")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    // GET all telemetry
    @GetMapping
    public ResponseEntity<List<Telemetry>> getAllTelemetry() {
        return ResponseEntity.ok(
                telemetryService.getAllTelemetry()
        );
    }

    // GET telemetry by ID
    @GetMapping("/{id}")
    public ResponseEntity<Telemetry> getTelemetryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                telemetryService.getTelemetryById(id)
        );
    }

    // POST create telemetry
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTelemetry(
            @Valid @RequestBody Telemetry telemetry) {

        Map<String, Object> response =
                telemetryService.createTelemetry(telemetry);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // PUT update telemetry
    @PutMapping("/{id}")
    public ResponseEntity<Telemetry> updateTelemetry(
            @PathVariable Long id,
            @Valid @RequestBody Telemetry updatedTelemetry) {

        return ResponseEntity.ok(
                telemetryService.updateTelemetry(id, updatedTelemetry)
        );
    }

    // DELETE telemetry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelemetry(
            @PathVariable Long id) {

        telemetryService.deleteTelemetry(id);

        return ResponseEntity.noContent().build();
    }
}