package com.ikon.chat.messagingservice.controller;

import com.ikon.chat.messagingservice.dto.MessageDTO;
import com.ikon.chat.messagingservice.service.MessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessagingController {

	@Autowired
    private MessagingService messagingService;

	@PostMapping("/sendmessage")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
        	System.out.println("I am inside of send message");
        	System.out.println(messageDTO);
            messagingService.processMessage(messageDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
        }
    }
    
    @GetMapping("/recievemessage")
    public String getMessage() {
        return "Hello from Messaging Service!";
    }
}
