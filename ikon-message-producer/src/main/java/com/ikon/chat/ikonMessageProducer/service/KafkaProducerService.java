package com.ikon.chat.ikonMessageProducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikon.chat.ikonMessageProducer.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.private.name}")
    private String privateTopicName;

    private final ObjectMapper objectMapper = new ObjectMapper();
    public void sendMessage(String key, String senderNumber, String recipientNumber, String bodyMessage) throws JsonProcessingException {
        MessageDto tempMessage = new MessageDto();
        tempMessage.setSenderNumber(senderNumber);
        tempMessage.setRecipientNumber(recipientNumber);
        tempMessage.setBody(bodyMessage);

        String jsonString = objectMapper.writeValueAsString(tempMessage);
        kafkaTemplate.send(privateTopicName,key, jsonString);
    }
}
