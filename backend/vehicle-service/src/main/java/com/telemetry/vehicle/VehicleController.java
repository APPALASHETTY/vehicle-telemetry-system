package com.telemetry.vehicle;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // GET all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // POST create vehicle
    @PostMapping
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    // GET vehicle by VIN
    @GetMapping("/{vin}")
    public Vehicle getVehicleByVin(@PathVariable String vin) {
        return vehicleService.getVehicleByVin(vin);
    }

    // PUT update vehicle
    @PutMapping("/{vin}")
    public Vehicle updateVehicle(
            @PathVariable String vin,
            @Valid @RequestBody Vehicle updatedVehicle) {

        return vehicleService.updateVehicle(vin, updatedVehicle);
    }

    // DELETE vehicle
    @DeleteMapping("/{vin}")
    public void deleteVehicle(@PathVariable String vin) {
        vehicleService.deleteVehicle(vin);
    }
}