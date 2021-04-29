package com.khanhnhb.springweb.controllers;

import com.khanhnhb.springweb.service.FileStorageService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@SpringBootTest
public class FileControllerTest {
    @InjectMocks
    FileController fileController;

    @Mock
    FileStorageService fileStorageService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpload() throws IOException {
        when(fileStorageService.storeFile(isA(MultipartFile.class))).thenReturn(isA(String.class));
        FileInputStream fis = new FileInputStream( "./logs/" + "ppa.png");
        MultipartFile file = new MockMultipartFile("file", fis);
        ResponseEntity<Boolean> response = fileController.upload(file);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().booleanValue(), true);
    }

    @Test
    public void testDownload() throws IOException {
        when(fileStorageService.loadFile(isA(String.class))).thenReturn(isA(byte[].class));
        ResponseEntity<byte[]> response = fileController.download("file");
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}