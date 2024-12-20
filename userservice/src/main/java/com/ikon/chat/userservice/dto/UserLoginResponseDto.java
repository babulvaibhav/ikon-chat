package com.ikon.chat.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {

    private UUID ticket;
    private UUID userId;

}
