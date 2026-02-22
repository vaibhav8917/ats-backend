package com.ats.service;

import com.ats.dto.JobRequest;
import com.ats.dto.JobResponse;
import com.ats.entity.Job;
import org.springframework.data.domain.Page;

public interface JobService {
    JobResponse createJob(JobRequest request);

    Page<JobResponse> getAllJobs(int page, int size);
}
