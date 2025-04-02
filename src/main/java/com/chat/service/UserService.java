package com.chat.service;


import com.chat.dto.request.RegistrationRequest;

public interface UserService {
    void register(RegistrationRequest user);

}
