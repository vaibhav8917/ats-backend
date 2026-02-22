package com.ats.controller;

import com.ats.dto.FeedbackRequest;
import com.ats.dto.FeedbackResponse;
import com.ats.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PreAuthorize("hasRole('INTERVIEWER')")
    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(
            @Valid @RequestBody FeedbackRequest request) {

        return ResponseEntity.ok(feedbackService.submitFeedback(request));
    }
}
