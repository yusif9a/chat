package com.chat.dto.request;

import com.chat.model.enums.Gender;
import com.chat.model.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class RegistrationRequest {
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 255, message = "First name cannot be longer than 255 characters")
    String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 255, message = "Last name cannot be longer than 255 characters")
    String lastName;
    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 150, message = "Age must be less than 150")
    Integer age;
    @NotNull(message = "Gender could not be empty")
    Gender gender;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password must be between 6 and 255 characters")
    String password;
    @NotNull(message = "Role cannot be null")
    UserRole role;
}
