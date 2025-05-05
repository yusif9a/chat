package com.chat.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class ImageCompressor {

    private String ffmpegPath = "C:\\ffmpeg\\ffmpeg";



    public void compressImageToWebP(String inImagePath, String outWebPPath, String outJpgPath) {
        String newOutWebpPath = outWebPPath.replace("-compress.webp", ".webp");
        String[] webpCommand = {
                ffmpegPath,
                "-y",
                "-threads", "1",
                "-benchmark",
                "-i", inImagePath,
                "-vf", "scale='min(1280,iw)':min'(720,ih)':force_original_aspect_ratio=decrease", // Ölçeklendirme komutu
                "-compression_level", "7", // WebP sıkıştırma seviyesi
                newOutWebpPath
        };
        String newOutJpgPath = outJpgPath.replace("-compress.jpg", ".jpg");
        String[] jpgCommand = {
                ffmpegPath,
                "-y",
                "-threads", "1",
                "-benchmark",
                "-i", inImagePath,
                "-vf", "scale='min(1280,iw)':min'(720,ih)':force_original_aspect_ratio=decrease", // Ölçeklendirme komutu
                "-q:v", "5",
                "-compression_level", "7", // JPG sıkıştırma seviyesi (0-9)
                newOutJpgPath
        };

        if (outWebPPath == null || outJpgPath == null) {
            throw new IllegalArgumentException("Output paths (outWebPPath or outJpgPath) cannot be null.");
        }

        try {
            // WebP'ye dönüştürme işlemi
            ProcessBuilder webpPb = new ProcessBuilder(webpCommand);
            webpPb.inheritIO();
            webpPb.redirectErrorStream(true);
            Process webpProcess = webpPb.start();
            try (var reader = new BufferedReader(new InputStreamReader(webpProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("WebP: " + line);
                }
            }
            int webpExitCode = webpProcess.waitFor();
            if (webpExitCode == 0) {
                System.out.println("Image successfully converted to WebP format.");
            } else {
                System.out.println("Failed to convert image to WebP format.");
            }

            // Jpg'ye dönüştürme işlemi
            ProcessBuilder jpgPb = new ProcessBuilder(jpgCommand);
            jpgPb.inheritIO();
            jpgPb.redirectErrorStream(true);
            jpgPb.redirectOutput(new File("ffmpeg_output.log"));
            Process jpgProcess = jpgPb.start();
            try (var reader = new BufferedReader(new InputStreamReader(jpgProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("JPG: " + line);
                }
            }
            int jpgExitCode = jpgProcess.waitFor();
            if (jpgExitCode == 0) {
                System.out.println("Image successfully converted to JPG format.");
            } else {
                System.out.println("Failed to convert image to JPG format.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}

