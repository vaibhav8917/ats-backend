package com.ats.dto;

import com.ats.entity.Recommendation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackRequest {
    @NotNull
    private Long interviewId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer technicalRating;

    @NotNull
    private Integer communicationRating;

    private String remarks;

    @NotNull
    private Recommendation recommendation;
}
