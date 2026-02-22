package com.ats.controller;

import com.ats.dto.ApplicationRequest;
import com.ats.dto.ApplicationResponse;
import com.ats.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping
    public ResponseEntity<ApplicationResponse> applyForJob(
            @Valid @RequestBody ApplicationRequest request) {

        return ResponseEntity.ok(applicationService.applyForJob(request));
    }
}
