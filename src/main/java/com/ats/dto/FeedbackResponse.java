package com.ats.dto;

import com.ats.entity.Recommendation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponse {
    private Long id;
    private Long interviewId;
    private Integer technicalRating;
    private Integer communicationRating;
    private String remarks;
    private Recommendation recommendation;
    private LocalDateTime submittedAt;
}
