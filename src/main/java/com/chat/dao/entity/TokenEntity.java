package com.chat.dao.entity;


import com.chat.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
    LocalDateTime createAt;
    @Enumerated(EnumType.STRING)
    TokenType tokenType;

    boolean revoked;
    boolean expired;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private UserEntity user;

}
