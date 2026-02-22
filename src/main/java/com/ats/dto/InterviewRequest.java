package com.ats.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRequest {
    @NotNull
    private Long applicationId;

    @NotNull
    private LocalDateTime scheduledAt;
}
