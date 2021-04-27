package com.khanhnhb.springweb.controllers;

import com.khanhnhb.springweb.config.FileStorageConfiguration;
import com.khanhnhb.springweb.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Controller
public class FileController {

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/uploads")
    public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String path = fileStorageService.storeFile(file);
        System.out.println(path);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable("fileName") String fileName) throws IOException {
        System.out.println(fileName);
        byte[] bytes = fileStorageService.loadFile(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
