package com.chat.service.impl;

import com.chat.configuration.JwtService;
import com.chat.dao.entity.TokenEntity;
import com.chat.dao.entity.UserEntity;
import com.chat.dao.repository.TokenRepository;
import com.chat.dao.repository.UserRepository;
import com.chat.dto.request.RegistrationRequest;
import com.chat.service.RegistrationStrategy;
import com.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.chat.model.enums.TokenType.BEARER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final RegistrationStrategy registrationStrategy;
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


    private boolean checkIfExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private UserEntity getUserEntity(RegistrationRequest request){
        var user = new UserEntity();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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
