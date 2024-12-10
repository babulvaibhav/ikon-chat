package com.ikon.chat.ikonMessageConsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikon.chat.ikonMessageConsumer.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "${message.topic.private.name}", containerFactory = "mobileContainerFactory")
    public void listenerMobile(String message) throws JsonProcessingException {

        MessageDto messageDto = objectMapper.readValue(message, MessageDto.class);
        System.out.println(messageDto.getSenderNumber() +" : "+ messageDto.getBody());


    }


}
