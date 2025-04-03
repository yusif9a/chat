package com.chat.dto.request;

import com.chat.model.enums.Gender;
import com.chat.model.enums.UserRole;
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
    @Enumerated(EnumType.STRING)
    UserRole role;

//    public CreateAccountRequest from(RegistrationRequest request){
//        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
//        createAccountRequest.firstname = request.getFirstName();
//        createAccountRequest.lastname = request.getLastName();
//        createAccountRequest.age = request.getAge();
//        createAccountRequest.gender = request.getGender();
//        createAccountRequest.setRole(request.getRole());
//
//        return createAccountRequest;
//    }

    public static CreateAccountRequest from(RegistrationRequest request){
       return CreateAccountRequest.builder()
               .age(request.getAge())
               .firstname(request.getFirstName())
               .gender(request.getGender())
               .lastname(request.getLastName())
               .build();

    }
}
