package com.chat.service;

import com.chat.dto.request.MessageRequest;
import com.chat.dto.response.MessageResponse;

public interface MessageService {

    MessageResponse sendMessage(MessageRequest messageRequest);
}
