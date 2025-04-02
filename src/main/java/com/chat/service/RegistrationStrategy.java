package com.chat.service;

import com.chat.dto.request.CreateAccountRequest;
import com.chat.dto.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationStrategy {

    private final AccountService accountService;
    CreateAccountRequest createAccountRequest;
    @Async
    public void register(RegistrationRequest registrationRequest) {
        switch (registrationRequest.getRole()) {
            case USER->accountService.createAccount(createAccountRequest.from(registrationRequest));
        }
    }
}
