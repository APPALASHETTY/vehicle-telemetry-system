package com.telemetry.vehicle;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "VIN must not be blank")
    @Column(nullable = false, unique = true)
    private String vin;

    @NotBlank(message = "Model must not be blank")
    @Column(nullable = false)
    private String model;

    @NotBlank(message = "Manufacturer must not be blank")
    @Column(nullable = false)
    private String manufacturer;

    @NotNull(message = "Manufacturing year is required")
    @Min(value = 1886, message = "Manufacturing year must be valid")
    private Integer manufacturingYear;

    public Vehicle() {
    }

    public Vehicle(String vin, String model, String manufacturer, Integer manufacturingYear) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.manufacturingYear = manufacturingYear;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(Integer manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }
}