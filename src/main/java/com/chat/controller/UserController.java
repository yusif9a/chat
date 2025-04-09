package com.chat.controller;

import com.chat.dao.entity.UserEntity;
import com.chat.dto.request.RegistrationRequest;
import com.chat.dto.request.UserLoginRequest;
import com.chat.dto.response.UserLoginResponse;
import com.chat.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void userRegister(@RequestBody @Valid RegistrationRequest request) {

        userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request){
        return userService.login(request);
    }

}
