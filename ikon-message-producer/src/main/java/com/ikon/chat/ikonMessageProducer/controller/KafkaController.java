package com.ikon.chat.ikonMessageProducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ikon.chat.ikonMessageProducer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;


    @PostMapping("/sendMessage")
    public void sendMessage(@RequestParam("k") String key, @RequestParam String senderNumber, @RequestParam String recipientNumber, @RequestParam String bodyMessage) throws JsonProcessingException {
        kafkaProducerService.sendMessage(key,senderNumber,recipientNumber,bodyMessage);
    }
}
