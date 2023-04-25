package com.scaler.parkinglot.strategies.spotassignment;

import com.scaler.parkinglot.models.Gate;
import com.scaler.parkinglot.models.ParkingSpot;
import com.scaler.parkinglot.models.VehicleType;
import com.scaler.parkinglot.repositories.ParkingSpotRepository;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {
    private ParkingSpotRepository parkingSpotRepository;

    public RandomSpotAssignmentStrategy(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
        return null;
    }
}
