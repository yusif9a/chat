package com.chat.service;

import com.chat.dto.request.CreateAccountRequest;
import com.chat.dto.request.RegistrationRequest;
import com.chat.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RegistrationStrategy {

    private final AccountService accountService;
    @Async
    public void register(RegistrationRequest registrationRequest) {
        if (Objects.requireNonNull(registrationRequest.getRole()) == UserRole.USER) {
            if ( CreateAccountRequest.from(registrationRequest)== null) {
                throw new IllegalArgumentException("Request body cannot be null");
            }
            accountService.createAccount(CreateAccountRequest.from(registrationRequest));
        }
    }
}
