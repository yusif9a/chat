package com.chat.dto.request;

import com.chat.model.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@FieldDefaults(level = PRIVATE)
public class CreateAccountRequest {

    String firstname;
    String lastname;
    Integer age;
    @Enumerated(EnumType.STRING)
    Gender gender;

    public CreateAccountRequest from(RegistrationRequest request){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.firstname = firstname;
        createAccountRequest.lastname = lastname;
        createAccountRequest.age = age;
        createAccountRequest.gender = gender;
        return createAccountRequest;
    }
}
