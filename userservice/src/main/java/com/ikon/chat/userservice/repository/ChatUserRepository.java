package com.ikon.chat.userservice.repository;

import com.ikon.chat.userservice.entity.ChatUser;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, UUID> {
}
