package com.chat.service;


import com.chat.dto.request.RegistrationRequest;
import com.chat.dto.request.UserLoginRequest;
import com.chat.dto.response.UserLoginResponse;

public interface UserService {
    void register(RegistrationRequest user);

    UserLoginResponse login(UserLoginRequest request);

}
