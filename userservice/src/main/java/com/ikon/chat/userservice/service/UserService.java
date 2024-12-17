package com.ikon.chat.userservice.service;

import com.ikon.chat.userservice.entity.ChatUser;
import com.ikon.chat.userservice.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	@Autowired
    private  ChatUserRepository chatUserRepository;

    public ChatUser saveUser(ChatUser user) {
        return chatUserRepository.save(user);
    }

    public ChatUser getUserById(UUID userId) {
        return chatUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public List<ChatUser> getAllChatUsers() {
        return chatUserRepository.findAll();
    }
}
