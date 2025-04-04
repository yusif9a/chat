package com.chat.dto.request;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    Long receiverId;
    String content;

}
