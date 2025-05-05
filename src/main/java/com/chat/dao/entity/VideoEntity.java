package com.chat.dao.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)

public class VideoEntity extends BaseMediaEntity{
    private Long duration;
    private String resolution;
    private Long size;
}
