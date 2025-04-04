package com.chat.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    Long id;
    String content;
    String senderEmail;
    String receiver;
    LocalDateTime sendAt;
}
