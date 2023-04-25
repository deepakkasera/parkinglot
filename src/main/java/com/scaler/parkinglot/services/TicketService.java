package com.scaler.parkinglot.services;

import com.scaler.parkinglot.exceptions.InvalidRequestInfoException;
import com.scaler.parkinglot.exceptions.NoParkingSlotAvailableException;
import com.scaler.parkinglot.models.*;
import com.scaler.parkinglot.repositories.TicketRepository;
import com.scaler.parkinglot.strategies.spotassignment.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {
    private VehicleService vehicleService;
    private GateService gateService;

    private SpotAssignmentStrategy spotAssignmentStrategy;

    private TicketRepository ticketRepository;

    public TicketService(VehicleService vehicleService, GateService gateService,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         TicketRepository ticketRepository) {
        this.vehicleService = vehicleService;
        this.gateService = gateService;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws InvalidRequestInfoException, NoParkingSlotAvailableException {
        //1. Using the vehicle number, fetch the vehicle from DB.
        // vehicleService.getVehicleByNumber
        // vehicleRepository.fetchVehicleDetails.
        //2. Invoke gateService.

        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);

        if (vehicle == null) {
            vehicleService.registerVehicle(vehicleNumber, vehicleType);
        }
        Gate gate = gateService.getGate(gateId);
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.getOperator());
        ticket.setGate(gate);
        // ticket.setParkingSpot();

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);

        if (parkingSpot == null) {
            throw new NoParkingSlotAvailableException("No Parking slot available");
        }

        ticket.setParkingSpot(parkingSpot);
        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }
}
