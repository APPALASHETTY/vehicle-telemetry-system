package com.telemetry.telemetry;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class TelemetryDataGenerator {

    private final TelemetryService telemetryService;
    private final VehicleClient vehicleClient;

    public TelemetryDataGenerator(
            TelemetryService telemetryService,
            VehicleClient vehicleClient) {

        this.telemetryService = telemetryService;
        this.vehicleClient = vehicleClient;
    }

    @Scheduled(fixedRate = 5000)
    public void generateTelemetry() {

        String vin = "VIN12345";

        // Check whether vehicle exists in Vehicle Service
        if (!vehicleClient.vehicleExists(vin)) {

            System.out.println(
                    "Telemetry rejected: Vehicle not found for VIN=" + vin
            );

            return;
        }

        Telemetry telemetry = new Telemetry();

        // Generate telemetry values
        double speed =
                ThreadLocalRandom.current().nextDouble(80.0, 140.0);

        double engineTemperature =
                ThreadLocalRandom.current().nextDouble(90.0, 120.0);

        double latitude =
                ThreadLocalRandom.current().nextDouble(17.3800, 17.3900);

        double longitude =
                ThreadLocalRandom.current().nextDouble(78.4800, 78.4900);

        telemetry.setVin(vin);
        telemetry.setSpeed(speed);
        telemetry.setEngineTemperature(engineTemperature);
        telemetry.setLatitude(latitude);
        telemetry.setLongitude(longitude);
        telemetry.setTimestamp(
                java.time.LocalDateTime.now().toString()
        );

        telemetryService.createTelemetry(telemetry);

        System.out.println(
                "Telemetry generated: "
                        + "VIN=" + telemetry.getVin()
                        + " | Speed=" + telemetry.getSpeed()
                        + " | Temperature=" + telemetry.getEngineTemperature()
                        + " | Latitude=" + telemetry.getLatitude()
                        + " | Longitude=" + telemetry.getLongitude()
                        + " | Timestamp=" + telemetry.getTimestamp()
        );
    }
}