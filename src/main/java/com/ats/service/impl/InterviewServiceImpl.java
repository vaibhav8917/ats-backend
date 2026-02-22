package com.ats.service.impl;

import com.ats.dto.InterviewRequest;
import com.ats.dto.InterviewResponse;
import com.ats.entity.*;
import com.ats.exception.DuplicateResourceException;
import com.ats.exception.ResourceNotFoundException;
import com.ats.repository.ApplicationRepository;
import com.ats.repository.InterviewRepository;
import com.ats.repository.UserRepository;
import com.ats.service.InterviewService;
import com.ats.util.InterviewMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public InterviewResponse scheduleInterview(InterviewRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User interviewer = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (interviewer.getRole() != Role.INTERVIEWER) {
            throw new RuntimeException("Not authorized");
        }

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (application.getStatus() == ApplicationStatus.REJECTED
                || application.getStatus() == ApplicationStatus.SELECTED) {
            throw new RuntimeException("Interview cannot be scheduled for finalized application");
        }

        if (interviewRepository.existsByApplicationId(request.getApplicationId())) {
            throw new DuplicateResourceException("Interview already scheduled for this application");
        }

//        User interviewer = userRepository.findById(request.getInterviewerId())
//                .orElseThrow(() -> new ResourceNotFoundException("Interviewer not found"));

        if (interviewer.getRole() != Role.INTERVIEWER) {
            throw new RuntimeException("User is not an interviewer");
        }

        Interview interview = Interview.builder()
                .application(application)
                .interviewer(interviewer)
                .scheduledAt(request.getScheduledAt())
                .status(InterviewStatus.SCHEDULED)
                .build();

        // 🔹 Update application status
        application.setStatus(ApplicationStatus.INTERVIEW_SCHEDULED);

        Interview savedInterview = interviewRepository.save(interview);
        System.out.println("Scheduled At: " + request.getScheduledAt());
        return InterviewMapper.toResponse(savedInterview);
    }
}
