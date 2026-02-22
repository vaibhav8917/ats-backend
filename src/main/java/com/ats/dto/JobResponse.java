package com.ats.dto;

import com.ats.entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String salaryRange;
    private JobStatus status;
    private Long createdByUserId;
    private LocalDateTime createdAt;
}
