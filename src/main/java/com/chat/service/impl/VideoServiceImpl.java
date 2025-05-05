package com.chat.service.impl;

import com.chat.service.VideoService;
import com.chat.util.VideoCompressor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {

    private  final VideoCompressor videoCompressor;
    private final Path root = Paths.get("uploads/video");
    private final static long maxVideoSize = 50*1000*1000;

}
