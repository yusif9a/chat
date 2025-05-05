package com.chat.dao.entity;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageEntity extends BaseMediaEntity{

    String dimension;
    String size;
}
