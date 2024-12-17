package com.ikon.chat.userservice.controller;

import com.ikon.chat.userservice.entity.ChatUser;
import com.ikon.chat.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ChatUser> registerUser(@RequestBody ChatUser user) {
    	System.out.println("Inside registerUser: " + user.toString());
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<ChatUser> getUser(@PathVariable UUID  userId) {
    	System.out.println("Inside get User: "+userId);
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    
    @GetMapping("/getAllusers")
    public List<ChatUser> getAllUsers() {
        return userService.getAllChatUsers();
    }
    
}
