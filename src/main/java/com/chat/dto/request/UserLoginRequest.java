package com.chat.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    String email;
    String password;

}
