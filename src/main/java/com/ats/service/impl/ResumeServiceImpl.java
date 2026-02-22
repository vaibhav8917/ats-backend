package com.ats.service.impl;

import com.ats.entity.Application;
import com.ats.entity.Resume;
import com.ats.exception.DuplicateResourceException;
import com.ats.exception.ResourceNotFoundException;
import com.ats.repository.ApplicationRepository;
import com.ats.repository.ResumeRepository;
import com.ats.service.ResumeService;
import com.ats.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ApplicationRepository applicationRepository;
    private final ResumeRepository resumeRepository;
    private final FileStorageUtil fileStorageUtil;

    @Override
    public void uploadResume(Long applicationId, MultipartFile file) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application Not Found"));
        // Check if resume already exists
        if (resumeRepository.existsByApplicationId(applicationId)) {
            throw new DuplicateResourceException("Resume already uploaded for this application");
        }

        try {
            String fileName = fileStorageUtil.saveFile(file);

            Resume resume = Resume.builder()
                    .fileName(fileName)
                    .filePath("uploads/resumes/" + fileName)
                    .fileType(file.getContentType())
                    .application(application)
                    .build();

            resumeRepository.save(resume);
        }catch (Exception e){
            throw new RuntimeException("File Upload Failed");
        }
    }
}
