package com.chat.producer;

import com.chat.dto.request.KafkaMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
//   private final KafkaTemplate<String, KafkaMessageDto> kafkaTemplate;

    private static final String TOPIC = "my_topic";

    private final KafkaTemplate<String, KafkaMessageDto> kafkaTemplate;

//    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void sendMessage(KafkaMessageDto message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Message sent: " + message);
    }
}
