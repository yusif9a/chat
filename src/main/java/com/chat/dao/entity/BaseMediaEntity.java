package com.chat.dao.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseMediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String imageName;
    String imagePath;
    String type;
    String userId;

}
