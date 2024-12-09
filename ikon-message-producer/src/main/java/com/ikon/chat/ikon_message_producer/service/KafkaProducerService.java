package com.ikon.chat.ikon_message_producer.service;

import com.ikon.chat.ikon_message_producer.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Value(value = "${message.topic.private.name}")
    private String privateTopicName;

    public void sendMessage(String key, String senderNumber, String recipientNumber, String bodyMessage){
            MessageDto tempMessage = new MessageDto();
            tempMessage.setSenderNumber(senderNumber);
            tempMessage.setRecipientNumber(recipientNumber);
            tempMessage.setBody(bodyMessage);
            kafkaTemplate.send(privateTopicName, key, tempMessage
        );
    }
}
