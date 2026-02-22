package com.ats.util;

import com.ats.dto.ApplicationResponse;
import com.ats.entity.Application;

public class ApplicationMapper {
    public static ApplicationResponse toResponse(Application app) {
        return ApplicationResponse.builder()
                .id(app.getId())
                .candidateId(app.getCandidate().getId())
                .jobId(app.getJob().getId())
                .status(app.getStatus())
                .appliedAt(app.getAppliedAt())
                .build();
    }
}
