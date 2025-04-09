package com.chat.service;

import com.chat.dto.request.MessageRequest;
import com.chat.dto.response.MessageResponse;

import java.security.Principal;

public interface MessageService {

    MessageResponse sendMessage(MessageRequest messageRequest, Principal principal);
}
