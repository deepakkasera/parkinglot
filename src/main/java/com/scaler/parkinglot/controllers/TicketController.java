package com.scaler.parkinglot.controllers;

import com.scaler.parkinglot.dto.GenerateTicketRequestDto;
import com.scaler.parkinglot.dto.GenerateTicketResponseDto;
import com.scaler.parkinglot.exceptions.InvalidRequestInfoException;
import com.scaler.parkinglot.exceptions.NoParkingSlotAvailableException;
import com.scaler.parkinglot.models.ResponseStatus;
import com.scaler.parkinglot.models.Ticket;
import com.scaler.parkinglot.services.TicketService;

import java.sql.PreparedStatement;

public class TicketController {
    //Controller class should be as light as possible.
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

//    public Ticket generateTicket(Vehicle vehicle, Gate gate) {
//
//    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request) {

        // 1. VehicleService -> fetch the Vehicle details,
        // GateService -> fetch the Gate details,
        // TicketService -> pass the vehicle & gate details, create Ticket object.

        // 2. TicketService -
            // VehicleService - getVehicle()
            // GateService - getGateDetails()
            // createTicket

        GenerateTicketResponseDto response = new GenerateTicketResponseDto();
        try {
            Ticket ticket = ticketService.generateTicket(request.getVehicleNumber(),
                    request.getVehicleType(), request.getGateId());

            response.setTicket(ticket);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidRequestInfoException exception) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        } catch (NoParkingSlotAvailableException e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

}
//Problems with directly including the Models inside the methods.
// 1. Models are getting exposed to the external clients, that is not desirable.
// 2. If new param is getting added in the method, then the current client will start failing.
// 3. Client will have to make a lot of GET requests to get the details of Vehicle, Gate etc.

// DTO -> Data Transfer Objects. -> Kind of Model classes, used for request/response.

//Dependency Inject