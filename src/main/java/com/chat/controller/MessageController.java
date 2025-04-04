package com.chat.controller;

import com.chat.dto.request.MessageRequest;
import com.chat.dto.response.MessageResponse;
import com.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/message")
public class MessageController {


    private final MessageService messageService;

    @PostMapping("/send_message")

    public MessageResponse sendMessage(@RequestBody MessageRequest messageRequest){
        return messageService.sendMessage(messageRequest);
    }

}
