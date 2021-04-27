package com.khanhnhb.springweb.services;

import com.khanhnhb.springweb.config.FileStorageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfiguration fileStorageConfiguration) {
        this.fileStorageLocation = Paths
                .get(fileStorageConfiguration.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {

        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        Path targetFile = fileStorageLocation.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        return targetFile.toAbsolutePath().toString();
    }

    public byte[] loadFile(String fileName) throws IOException {
        byte[] fileData = Files.readAllBytes(
                new File(fileStorageLocation.toString() + "/" + fileName).toPath());
        return fileData;
    }
}
