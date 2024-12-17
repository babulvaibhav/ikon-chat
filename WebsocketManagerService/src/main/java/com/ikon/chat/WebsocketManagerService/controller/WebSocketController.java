package com.ikon.chat.WebsocketManagerService.controller;

import com.ikon.chat.WebsocketManagerService.dto.UserMessageDto;
import com.ikon.chat.WebsocketManagerService.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebSocketController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/message/group/{groupId}")
    //@SendTo("/topic/group/{groupId}")
    public String sendGroupMessage(@DestinationVariable String groupId, @Payload UserMessageDto userMessageDto) throws InterruptedException {
        messageService.sendMessageToGroup(groupId, userMessageDto);
        System.out.println("Message sent to group: " + groupId);
        return "send";
    }

    @MessageMapping("/message/user")
    public String sendPrivateMessage(@Payload UserMessageDto userMessageDto) {
        String userId = userMessageDto.getReceiverId().toString();
        //messageService.sendMessageToUser(userId, userMessageDto);

        messageService.sendToUserTopic(userMessageDto);
        System.out.println("Private Message: " + userId + userMessageDto.getMessageContent());
        return "SEND";
    }


}
