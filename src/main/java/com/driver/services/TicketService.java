package com.driver.services;


import com.driver.EntryDto.BookTicketEntryDto;
import com.driver.model.Passenger;
import com.driver.model.Ticket;
import com.driver.model.Train;
import com.driver.repository.PassengerRepository;
import com.driver.repository.TicketRepository;
import com.driver.repository.TrainRepository;
import com.driver.transformer.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    TrainService trainService;


    public Integer bookTicket(BookTicketEntryDto bookTicketEntryDto)throws Exception{

        //Check for validity
        //Use bookedTickets List from the TrainRepository to get bookings done against that train
        // Incase the there are insufficient tickets
        // throw new Exception("Less tickets are available");
        //otherwise book the ticket, calculate the price and other details
        //Save the information in corresponding DB Tables
        //Fare System : Check problem statement
        //Incase the train doesn't pass through the requested stations
        //throw new Exception("Invalid stations");
        //Save the bookedTickets in the train Object
        //Also in the passenger Entity change the attribute bookedTickets by using the attribute bookingPersonId.
       //And the end return the ticketId that has come from db
        Train train = trainRepository.findById(bookTicketEntryDto.getTrainId()).get();

        if(!trainService.goesFromStation(bookTicketEntryDto.getFromStation(), train) || !trainService.goesFromStation(bookTicketEntryDto.getToStation(), train)){
            throw new Exception("Invalid stations");
        }
        Ticket ticket = TicketTransformer.dtoToTicket(bookTicketEntryDto);
        List<Passenger> passengers = new ArrayList<>();
        for(Integer id: bookTicketEntryDto.getPassengerIds()){
            passengers.add(passengerRepository.findById(id).get());
        }

        ticket.setTrain(trainRepository.findById(bookTicketEntryDto.getTrainId()).get());
        ticket.setPassengersList(passengers);
        Ticket savedTicket = ticketRepository.save(ticket);
        train.getBookedTickets().add(savedTicket);
        trainRepository.save(train);
       return savedTicket.getTicketId();

    }
}
