package com.chat.consumer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaDto {
    //private Long id;              1. add new properties
    private Long id;
    private String name;
    //private String surname;    2.  //  remove this property
}
