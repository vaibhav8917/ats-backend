package com.ats.dto;

import com.ats.entity.InterviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewResponse {
    private Long id;
    private Long applicationId;
    private Long interviewerId;
    private LocalDateTime scheduledAt;
    private InterviewStatus status;
}
