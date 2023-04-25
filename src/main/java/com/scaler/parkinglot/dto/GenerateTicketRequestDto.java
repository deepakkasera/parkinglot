package com.scaler.parkinglot.dto;

import com.scaler.parkinglot.models.VehicleType;

import java.util.PrimitiveIterator;

public class GenerateTicketRequestDto {
    private String vehicleNumber;

    private long gateId;

    private VehicleType vehicleType;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public long getGateId() {
        return gateId;
    }

    public void setGateId(long gateId) {
        this.gateId = gateId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
