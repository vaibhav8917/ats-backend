package com.ats.service.impl;

import com.ats.dto.ApplicationRequest;
import com.ats.dto.ApplicationResponse;
import com.ats.entity.*;
import com.ats.exception.DuplicateResourceException;
import com.ats.exception.ResourceNotFoundException;
import com.ats.repository.ApplicationRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UserRepository;
import com.ats.service.ApplicationService;
import com.ats.util.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public ApplicationResponse applyForJob(ApplicationRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

//        User candidate = userRepository.findById(request.getCandidateId())
//                .orElseThrow(() -> new ResourceNotFoundException("candidate not found"));

        if (candidate.getRole() != Role.CANDIDATE) {
            throw new RuntimeException("Only candidates can apply for jobs");
        }

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        boolean alreadyApplied = applicationRepository
                .existsByCandidateIdAndJobId(candidate.getId(), request.getJobId());

        if (alreadyApplied) {
            throw new DuplicateResourceException("Candidate already applied for this job");
        }

        Application application = Application.builder()
                .candidate(candidate)
                .job(job)
                .status(ApplicationStatus.APPLIED)
                .build();

        Application saved = applicationRepository.save(application);

        return ApplicationMapper.toResponse(saved);
    }
}
