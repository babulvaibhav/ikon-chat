package com.ikon.chat.messagingservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private UUID conversationId;

    @NotBlank(message = "Sender ID cannot be blank")
    private UUID senderId;

    @NotBlank(message = "Recipient ID cannot be blank")
    private UUID receiverId;

    @NotBlank(message = "Message content cannot be blank")
    private Map<String,Object> messageContent;

    private LocalDateTime messageSentAt;
}
