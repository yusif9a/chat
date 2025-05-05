package com.chat.dto.request;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Data
public class KafkaMessageDto implements Serializable {
    private String content;
    private String senderMessage;
    private String receiveMessage;
    private LocalDateTime createDate;
}
