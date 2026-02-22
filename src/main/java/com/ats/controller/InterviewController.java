package com.ats.controller;

import com.ats.dto.InterviewRequest;
import com.ats.dto.InterviewResponse;
import com.ats.service.InterviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {
    private final InterviewService interviewService;

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/schedule")
    public ResponseEntity<InterviewResponse> scheduleInterview(
            @Valid @RequestBody InterviewRequest requestDTO) {

        return ResponseEntity.ok(interviewService.scheduleInterview(requestDTO));
    }
}
