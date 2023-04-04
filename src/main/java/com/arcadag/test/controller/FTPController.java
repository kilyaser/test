package com.arcadag.test.controller;

import com.arcadag.test.dto.FTPFileDTO;
import com.arcadag.test.service.FTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class FTPController {
    private final FTPService ftpService;
    @Autowired
    public FTPController(FTPService ftpService) {
        this.ftpService = ftpService;
    }
    @GetMapping("/photos")
    public ResponseEntity<List<FTPFileDTO>> getPhotos() {
        List<FTPFileDTO> response;
        try {
            response = ftpService.getFilesWithPrefix();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.of(Optional.ofNullable(response));

    }
}
