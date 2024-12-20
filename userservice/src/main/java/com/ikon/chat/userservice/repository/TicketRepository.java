package com.ikon.chat.userservice.repository;

import com.ikon.chat.userservice.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class TicketRepository  {

    private static final String KEY_PREFIX = "ticketspace:ticket:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    public UUID save(Ticket ticket) {
        UUID ticketId = UUID.randomUUID();

        Map<String, String> ticketMap = Map.of(
                "USER_ID", ticket.getUSER_ID().toString(),
                "USER_NAME", ticket.getUSER_NAME()
        );

        Map<String, Object> userMap = Map.of(
                "TICKET", ticketId.toString()
        );
        redisTemplate.opsForHash().putAll(KEY_PREFIX + ticketId.toString(), ticketMap);

        redisTemplate.opsForHash().putAll(ticket.getUSER_ID().toString(), userMap);
        return ticketId;
    }
}
