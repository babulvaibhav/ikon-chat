package com.ikon.chat.userservice.service;

import com.ikon.chat.userservice.dto.UserLoginDto;
import com.ikon.chat.userservice.dto.UserLoginResponseDto;
import com.ikon.chat.userservice.entity.ChatUser;
import com.ikon.chat.userservice.entity.Ticket;
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

    @Autowired
    private TicketService ticketService;

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

    public UserLoginResponseDto userLoginService(UserLoginDto userLoginDto) {

       ChatUser user = null;

       if(userLoginDto!= null && userLoginDto.getUserLogin() !=null && userLoginDto.getPassword() != null){
          user = chatUserRepository.findByUserLogin(userLoginDto.getUserLogin());

          if(user == null){
              return null;
          }

          if(user.getUserLogin().equals(userLoginDto.getUserLogin()) && user.getPassword().equals(userLoginDto.getPassword())){
              UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();

              UUID ticket = ticketService.createTicket(user);

              userLoginResponseDto.setTicket(ticket);
              userLoginResponseDto.setUserId(user.getUserId());
              return userLoginResponseDto;
          }

       }
       return null;
    }
}
