package com.ats.dto;

import com.ats.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;
    private Long candidateId;
    private Long jobId;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
}
