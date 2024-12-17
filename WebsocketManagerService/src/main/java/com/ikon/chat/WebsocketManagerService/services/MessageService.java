package com.ikon.chat.WebsocketManagerService.services;

import com.ikon.chat.WebsocketManagerService.dto.UserMessageDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final SimpMessagingTemplate messagingTemplate;

    private final List<String> userOfGroupList = List.of(
            "550e8400-e29b-41d4-a716-446655440000",
            "123e4567-e89b-12d3-a456-426614174000"
    );

    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Send message to a specific group topic
    public void sendMessageToGroup(String groupId, UserMessageDto userMessageDto){
        messagingTemplate.convertAndSend("/topic/group/" + groupId, userMessageDto);

//        for(String userId : userOfGroupList ){
//            messagingTemplate.convertAndSendToUser(userId, "/queue/messages", userMessageDto);
//        }
    }

    // Send message to a specific user
    public void sendMessageToUser(String userId, UserMessageDto userMessageDto) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", userMessageDto);

    }

    public void sendToUserTopic(UserMessageDto userMessageDto) {
        String recipientTopic = "/queue/messages/" + userMessageDto.getReceiverId();
        messagingTemplate.convertAndSend(recipientTopic, userMessageDto);
    }
}
