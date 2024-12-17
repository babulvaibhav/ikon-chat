package com.ikon.chat.userservice.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // or GenerationType.AUTO
    private UUID  userId; // Unique identifier for each user

    private String userName; // Display name
}
