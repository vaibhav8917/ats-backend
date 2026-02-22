package com.ats.service.impl;

import com.ats.dto.JobRequest;
import com.ats.dto.JobResponse;
import com.ats.entity.Job;
import com.ats.entity.JobStatus;
import com.ats.entity.Role;
import com.ats.entity.User;
import com.ats.exception.ResourceNotFoundException;
import com.ats.repository.JobRepository;
import com.ats.repository.UserRepository;
import com.ats.service.JobService;
import com.ats.util.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public JobResponse createJob(JobRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User hr = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (hr.getRole() != Role.HR) {
            throw new RuntimeException("Only HR can create jobs");
        }

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .salaryRange(request.getSalaryRange())
                .status(JobStatus.OPEN)
                .createdBy(hr)
                .build();

        Job savedJob = jobRepository.save(job);
        return JobMapper.toResponse(savedJob);
    }

    @Override
    public Page<JobResponse> getAllJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return jobRepository.findAll(pageable)
                .map(JobMapper::toResponse);
    }
}
