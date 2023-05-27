package com.driver.transformer;

import com.driver.EntryDto.BookTicketEntryDto;
import com.driver.model.Ticket;
import com.driver.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TicketTransformer {
    @Autowired
    PassengerRepository passengerRepository;
    public static Ticket dtoToTicket(BookTicketEntryDto bookTicketEntryDto){
        Ticket ticket = new Ticket();
        ticket.setFromStation(bookTicketEntryDto.getFromStation());
        ticket.setToStation(bookTicketEntryDto.getToStation());
        int fare = (bookTicketEntryDto.getToStation().ordinal() - bookTicketEntryDto.getFromStation().ordinal()) * 300;
        ticket.setTotalFare(fare);
        return ticket;
    }
}
