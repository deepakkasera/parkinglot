package com.scaler.parkinglot.models;

public class ElectricParkingSpot extends BaseModel {
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public ElectricCharger getElectricCharger() {
        return electricCharger;
    }

    public void setElectricCharger(ElectricCharger electricCharger) {
        this.electricCharger = electricCharger;
    }

    private ParkingSpot parkingSpot;
    private ElectricCharger electricCharger;
}
