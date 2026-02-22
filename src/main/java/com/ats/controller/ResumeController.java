package com.ats.controller;

import com.ats.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resumes")
    public ResponseEntity<String> uploadResume(
            @RequestParam Long applicationId,
            @RequestParam MultipartFile file) {

        resumeService.uploadResume(applicationId, file);
        return ResponseEntity.ok("Resume uploaded successfully");
    }
}
