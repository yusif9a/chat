package com.chat.dao.entity;


import com.chat.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    @Enumerated(EnumType.STRING)
    TokenType tokenType;

    boolean revoked;
    boolean expired;

    @ManyToOne
    UserEntity user;

}
