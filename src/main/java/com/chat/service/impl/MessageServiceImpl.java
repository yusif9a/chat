package com.chat.service.impl;

import com.chat.dao.entity.MessageEntity;
import com.chat.dao.entity.UserEntity;
import com.chat.dao.repository.MessageRepository;
import com.chat.dao.repository.UserRepository;
import com.chat.dto.request.MessageRequest;
import com.chat.dto.response.MessageResponse;
import com.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.chat.util.SecurityUtil.getAuthenticatedEmail;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    @Override
    public MessageResponse sendMessage(MessageRequest messageRequest) {
        String senderEmail = getAuthenticatedEmail();
        UserEntity sender = userRepository.findByEmail(senderEmail).orElseThrow();

        UserEntity receiverEmail = userRepository.findById(messageRequest.getReceiverId()).orElseThrow();

        MessageEntity message = new MessageEntity();
        message.setContent(messageRequest.getContent());
        message.setSenderDate(LocalDateTime.now());
        message.setSender(sender);
        message.setReceiver(receiverEmail);
        messageRepository.save(message);
        return MessageResponse.builder()
                .sendAt(LocalDateTime.now())
                .content(messageRequest.getContent())
                .senderEmail(senderEmail)
                .receiver(receiverEmail.getEmail())
                .build();
    }
}
