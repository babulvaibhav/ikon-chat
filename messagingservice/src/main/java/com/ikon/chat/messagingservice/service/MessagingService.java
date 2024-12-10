package com.ikon.chat.messagingservice.service;

import com.ikon.chat.messagingservice.dto.MessageDTO;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MessagingService {

    private static final Logger log = LoggerFactory.getLogger(MessagingService.class);

    public void processMessage(MessageDTO messageDTO) {

        // Create REST client request to send the message to Kafka Producer Service
        String kafkaProducerServiceUrl = "http://kafka-producer-service/api/messages"; // Replace with the actual Kafka Producer Service URL

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageDTO> requestEntity = new HttpEntity<>(messageDTO, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(kafkaProducerServiceUrl, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Message successfully sent to Kafka Producer Service: {}", response.getBody());
            } else {
                log.error("Failed to send message to Kafka Producer Service: {}", response.getStatusCode());
            }
        } catch (RestClientException ex) {
            log.error("Error occurred while sending message to Kafka Producer Service", ex);
            throw new RuntimeException("Failed to send message to Kafka Producer Service", ex);
        }
    }

}
