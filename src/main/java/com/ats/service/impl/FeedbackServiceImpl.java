package com.ats.service.impl;

import com.ats.dto.FeedbackRequest;
import com.ats.dto.FeedbackResponse;
import com.ats.entity.*;
import com.ats.exception.DuplicateResourceException;
import com.ats.exception.ResourceNotFoundException;
import com.ats.repository.ApplicationRepository;
import com.ats.repository.InterviewFeedbackRepository;
import com.ats.repository.InterviewRepository;
import com.ats.service.FeedbackService;
import com.ats.util.FeedbackMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final InterviewRepository interviewRepository;
    private final InterviewFeedbackRepository interviewFeedbackRepository;
    private final ApplicationRepository applicationRepository;


    @Override
    @Transactional
    public FeedbackResponse submitFeedback(FeedbackRequest request) {
        //Find interview scheduled or not
        Interview interview = interviewRepository.findById(request.getInterviewId())
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found"));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        if (!interview.getInterviewer().getEmail().equals(email)) {
            throw new AccessDeniedException("Not your interview");
        }

        //Check feedback submitted
        if (interview.getStatus() != InterviewStatus.SCHEDULED) {
            throw new RuntimeException("Feedback already submitted or interview invalid");
        }

        //Check Feedback already given
        if (interviewFeedbackRepository.existsByInterviewId(request.getInterviewId())) {
            throw new DuplicateResourceException("Feedback already exists for this interview");
        }

        InterviewFeedback feedback = InterviewFeedback.builder()
                .interview(interview)
                .technicalRating(request.getTechnicalRating())
                .communicationRating(request.getCommunicationRating())
                .remarks(request.getRemarks())
                .recommendation(request.getRecommendation())
                .build();

        InterviewFeedback saved = interviewFeedbackRepository.save(feedback);

        //Update interview status
        interview.setStatus(InterviewStatus.COMPLETED);

        //Update application status
        Application application = interview.getApplication();

        if (request.getRecommendation() == Recommendation.SELECTED) {
            application.setStatus(ApplicationStatus.SELECTED);
        } else {
            application.setStatus(ApplicationStatus.REJECTED);
        }

        applicationRepository.save(application);

        return FeedbackMapper.toResponse(saved);
    }
}
