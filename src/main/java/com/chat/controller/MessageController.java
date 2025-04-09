package com.chat.controller;

import com.chat.dto.request.MessageRequest;
import com.chat.dto.response.MessageResponse;
import com.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {


    private final MessageService messageService;

    @PostMapping("/send_message")
    public MessageResponse message(@RequestBody MessageRequest messageRequest, Principal principal){
       return messageService.sendMessage(messageRequest,principal);
    }
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + auth);
        System.out.println("Principal: " + auth.getPrincipal());
        return ResponseEntity.ok("ok");
    }

}
