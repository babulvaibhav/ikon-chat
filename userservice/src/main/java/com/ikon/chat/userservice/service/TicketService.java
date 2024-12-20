package com.ikon.chat.userservice.service;

import com.ikon.chat.userservice.entity.ChatUser;
import com.ikon.chat.userservice.entity.Ticket;
import com.ikon.chat.userservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public UUID createTicket(ChatUser user){
        Ticket ticket = new Ticket();
        ticket.setUSER_ID(user.getUserId());
        ticket.setUSER_NAME(user.getUserName());
        return ticketRepository.save(ticket);
    }
}
