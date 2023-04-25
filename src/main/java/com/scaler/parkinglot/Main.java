package com.scaler.parkinglot;

import com.scaler.parkinglot.controllers.TicketController;
import com.scaler.parkinglot.dto.GenerateTicketRequestDto;
import com.scaler.parkinglot.dto.GenerateTicketResponseDto;
import com.scaler.parkinglot.models.ResponseStatus;
import com.scaler.parkinglot.models.Ticket;
import com.scaler.parkinglot.models.VehicleType;
import com.scaler.parkinglot.repositories.ParkingSpotRepository;
import com.scaler.parkinglot.repositories.TicketRepository;
import com.scaler.parkinglot.services.GateService;
import com.scaler.parkinglot.services.TicketService;
import com.scaler.parkinglot.services.VehicleService;
import com.scaler.parkinglot.strategies.spotassignment.RandomSpotAssignmentStrategy;
import com.scaler.parkinglot.strategies.spotassignment.SpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        VehicleService vehicleService = new VehicleService();
        GateService gateService = new GateService();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingSpotRepository);

        TicketService ticketService = new TicketService(vehicleService,
                gateService, spotAssignmentStrategy, ticketRepository);

        TicketController ticketController = new TicketController(ticketService);

        ObjectRegistry objectRegistry = new ObjectRegistry();
        objectRegistry.register("vehicleService", vehicleService);
        objectRegistry.register("ticketService", ticketService);

        GenerateTicketRequestDto request = new GenerateTicketRequestDto();
        request.setGateId(123);
        request.setVehicleNumber("HR26AXXXX");
        request.setVehicleType(VehicleType.LARGE);

        GenerateTicketResponseDto response = ticketController.generateTicket(request);
        if (response.getResponseStatus().equals(ResponseStatus.SUCCESS)) {
            Ticket ticket = response.getTicket();
        } else {
            // Response is FAILURE;
        }



    }
}

//

// Requirements :-
// 1. Operator should be able to generate the ticket.
// 2. Admin should be able to create a new parking lot.

//MVC -> Model, View, Controller.

// TicketService -> VS, GS, SAS, TR
// TC -> TicketService
// Topological Sorting.