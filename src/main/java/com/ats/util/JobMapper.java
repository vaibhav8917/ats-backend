package com.ats.util;

import com.ats.dto.JobResponse;
import com.ats.entity.Job;

public class JobMapper {
    public static JobResponse toResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .salaryRange(job.getSalaryRange())
                .status(job.getStatus())
                .createdByUserId(job.getCreatedBy().getId())
                .createdAt(job.getCreatedAt())
                .build();
    }
}
