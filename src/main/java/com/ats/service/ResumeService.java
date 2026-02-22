package com.ats.service;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {
    void uploadResume(Long applicationId, MultipartFile file);
}
