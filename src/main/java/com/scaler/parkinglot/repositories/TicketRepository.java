package com.scaler.parkinglot.repositories;

import com.scaler.parkinglot.models.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket> tickets = new HashMap<>();
    private Long ticketSequence = 0L;

    public Ticket save(Ticket ticket) {
        ticketSequence += 1;
        ticket.setId(ticketSequence);
        tickets.put(ticketSequence, ticket);
        return ticket;
    }
}
