package com.ats.controller;

import com.ats.dto.ApiResponse;
import com.ats.dto.JobRequest;
import com.ats.dto.JobResponse;
import com.ats.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PreAuthorize("hasRole('HR')")
    @PostMapping
    public ResponseEntity<ApiResponse<JobResponse>> createJob(@Valid @RequestBody JobRequest request){
//        return ResponseEntity.ok(jobService.createJob(request));
        return ResponseEntity.ok(
                ApiResponse.<JobResponse>builder()
                        .success(true)
                        .message("Job created successfully")
                        .data(jobService.createJob(request))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<Page<JobResponse>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(jobService.getAllJobs(page, size));
    }
}
