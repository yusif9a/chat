package com.chat.service.impl;

import com.chat.configuration.JwtService;
import com.chat.dao.entity.TokenEntity;
import com.chat.dao.entity.UserEntity;
import com.chat.dao.repository.TokenRepository;
import com.chat.dao.repository.UserRepository;
import com.chat.dto.request.CreateAccountRequest;
import com.chat.dto.request.RegistrationRequest;
import com.chat.dto.request.UserLoginRequest;
import com.chat.dto.response.UserLoginResponse;
import com.chat.model.enums.UserRole;
import com.chat.service.RegistrationStrategy;
import com.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.chat.mapper.UserMapper.USER_MAPPER;
import static com.chat.model.enums.TokenType.BEARER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final RegistrationStrategy registrationStrategy;
    private final AuthenticationManager authenticationManager;


    @Override
    public void register(RegistrationRequest request) {

        if (checkIfExists(request.getEmail())){
            throw new RuntimeException("User Already Exists");
        }

        var userEntity = getUserEntity(request);
        var saveUser = userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(saveUser);
        var refreshToken = jwtService.generateRefreshToken(saveUser);
        saveUserToken(userEntity, jwtToken);
        registrationStrategy.register(request);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        if (!checkIfExists(request.getEmail())){
            throw new RuntimeException();
        }

        UserEntity user = userRepository.findByEmail(request.getEmail()).get();

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())){
//            TokenEntity sessionToken = new TokenEntity();
//            sessionToken.setUser(user);
//            sessionToken.setCreateAt(LocalDateTime.now());
            String jwtToken = jwtService.generateToken(user);
//            sessionToken.setToken(jwtToken);


            saveUserToken(user, jwtToken);
            return UserLoginResponse.builder()
                    .token(jwtToken)
                    .build();

        }else {
            throw new RuntimeException("Username or Password incorrect !");
        }

    }


    private boolean checkIfExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private UserEntity getUserEntity(RegistrationRequest request){
        var user = USER_MAPPER.RegistrationRequest(request);
        user.setRegisterDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    private void saveUserToken(UserEntity user, String token) {
        var tokenEntity = new TokenEntity();
        tokenEntity.setUser(user);
        tokenEntity.setToken(token);
        tokenEntity.setTokenType(BEARER);
        tokenEntity.setExpired(false);
        tokenEntity.setRevoked(false);
        tokenRepository.save(tokenEntity);
    }


}
