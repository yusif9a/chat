package com.chat.dao.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)

public class AudioEntity extends BaseMediaEntity {
    private Long duration;
    private String bitrate;
    private Long size;
}
