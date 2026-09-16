package com.telemetry.vehicle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleByVin(String vin) {
        return vehicleRepository.findByVin(vin)
                .orElseThrow(() -> new VehicleNotFoundException(
                        "Vehicle not found with VIN: " + vin
                ));
    }

    public Vehicle updateVehicle(String vin, Vehicle updatedVehicle) {

        Vehicle existingVehicle = vehicleRepository.findByVin(vin)
                .orElseThrow(() -> new VehicleNotFoundException(
                        "Vehicle not found with VIN: " + vin
                ));

        existingVehicle.setModel(updatedVehicle.getModel());
        existingVehicle.setManufacturer(updatedVehicle.getManufacturer());
        existingVehicle.setManufacturingYear(updatedVehicle.getManufacturingYear());

        return vehicleRepository.save(existingVehicle);
    }

    public void deleteVehicle(String vin) {

        Vehicle vehicle = vehicleRepository.findByVin(vin)
                .orElseThrow(() -> new VehicleNotFoundException(
                        "Vehicle not found with VIN: " + vin
                ));

        vehicleRepository.delete(vehicle);
    }
}
