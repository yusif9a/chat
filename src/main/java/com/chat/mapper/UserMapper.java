package com.chat.mapper;


import com.chat.dao.entity.UserEntity;
import com.chat.dto.request.RegistrationRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

//@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
public enum UserMapper {

    USER_MAPPER;

    public UserEntity RegistrationRequest(RegistrationRequest request) {

        return UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .userRole(request.getRole())
                .build();
    }




}
