package com.ats.util;

import com.ats.dto.FeedbackResponse;
import com.ats.entity.InterviewFeedback;

public class FeedbackMapper {
    public static FeedbackResponse toResponse(InterviewFeedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .interviewId(feedback.getInterview().getId())
                .technicalRating(feedback.getTechnicalRating())
                .communicationRating(feedback.getCommunicationRating())
                .remarks(feedback.getRemarks())
                .recommendation(feedback.getRecommendation())
                .submittedAt(feedback.getSubmittedAt())
                .build();
    }
}
