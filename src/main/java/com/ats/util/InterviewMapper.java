package com.ats.util;

import com.ats.dto.InterviewResponse;
import com.ats.entity.Interview;

public class InterviewMapper {
    public static InterviewResponse toResponse(Interview interview) {
        return InterviewResponse.builder()
                .id(interview.getId())
                .applicationId(interview.getApplication().getId())
                .interviewerId(interview.getInterviewer().getId())
                .scheduledAt(interview.getScheduledAt())
                .status(interview.getStatus())
                .build();
    }
}
