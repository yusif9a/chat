package com.chat.dao.entity;


import com.chat.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String lastname;
    Integer age;
    @Enumerated(EnumType.STRING)
    Gender gender;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;


}
