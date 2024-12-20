package com.ikon.chat.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // or GenerationType.AUTO
    private UUID  userId; // Unique identifier for each user

    @Column(unique = true)
    private String userLogin;
    private String userName; // Display name
    private String userEmail;
    private String password;
}
