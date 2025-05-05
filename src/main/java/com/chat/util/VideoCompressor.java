package com.chat.util;

import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VideoCompressor {


    private final FFmpeg fFmpeg;
    private final FFprobe fFprobe;
    private final FFmpegExecutor fFmpegExecutor;

    public VideoCompressor() throws IOException {
        this.fFmpeg = new FFmpeg("C:\\ffmpeg\\ffmpeg");
        this.fFprobe = new FFprobe("C:\\ffprobe\\ffprobe");
        this.fFmpegExecutor = new FFmpegExecutor(fFmpeg,fFprobe);
    }

    public void compressVideo(String inputPath, String outputPath) throws IOException {

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputPath)
                .overrideOutputFiles(true)
                .addOutput(outputPath)
                    .setVideoCodec("libx264")
                .setAudioCodec("aac")
                .setAudioBitRate(500000)
                .setFormat("mp4")
                .done();
        fFmpegExecutor.createJob(builder).run();
        System.out.println("video compress done");

    }

}
