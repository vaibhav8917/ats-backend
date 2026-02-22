package com.ats.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStorageUtil {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {

        String absolutePath = new File(uploadDir).getAbsolutePath();
        File directory = new File(absolutePath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(directory, uniqueFileName);

        System.out.println("Saving file to: " + destination.getAbsolutePath());

        file.transferTo(destination);

        return uniqueFileName;
    }
}
