package com.telemetry.telemetry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class VehicleClient {

    private final RestClient restClient;

    public VehicleClient(
            RestClient.Builder builder,
            @Value("${vehicle.service.url}") String vehicleServiceUrl) {

        this.restClient = builder
                .baseUrl(vehicleServiceUrl)
                .build();
    }

    public boolean vehicleExists(String vin) {

        try {
            restClient.get()
                    .uri("/vehicles/{vin}", vin)
                    .retrieve()
                    .toBodilessEntity();

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}