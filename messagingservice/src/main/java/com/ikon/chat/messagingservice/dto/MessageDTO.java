package com.ikon.chat.messagingservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    @NotBlank(message = "Sender ID cannot be blank")
    private String senderId;

    @NotBlank(message = "Recipient ID cannot be blank")
    private String recipientId;

    @NotBlank(message = "Message content cannot be blank")
    private String content;
}
