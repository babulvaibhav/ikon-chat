package com.ikon.chat.WebsocketManagerService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageDto {

    private UUID senderId;
    private UUID receiverId;
    private LocalDateTime messageSentAt;
    private Map<String, Object> messageContent;

}
