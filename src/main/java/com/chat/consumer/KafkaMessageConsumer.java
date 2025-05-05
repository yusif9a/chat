package com.chat.consumer;

import com.chat.dao.entity.MessageEntity;
import com.chat.dao.entity.UserEntity;
import com.chat.dao.repository.MessageRepository;
import com.chat.dao.repository.UserRepository;
import com.chat.dto.request.KafkaMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageConsumer {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @KafkaListener(topics = "my_first_topic", groupId = "chat-group")
    public void consume(KafkaMessageDto messageDto) {
        UserEntity sender = userRepository.findByEmail(messageDto.getSenderMessage()).orElseThrow();
        UserEntity receiver = userRepository.findByEmail(messageDto.getReceiveMessage()).orElseThrow();



        MessageEntity messageEntity = new MessageEntity();
            messageEntity.setSender(sender);
            messageEntity.setReceiver(receiver);
            messageEntity.setSenderDate(messageDto.getSenderMessage() !=null ? messageDto.getCreateDate() : LocalDateTime.now());
            messageRepository.save(messageEntity);


    }
}
