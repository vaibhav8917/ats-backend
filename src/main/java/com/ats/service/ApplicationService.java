package com.ats.service;

import com.ats.dto.ApplicationRequest;
import com.ats.dto.ApplicationResponse;

public interface ApplicationService {
    ApplicationResponse applyForJob(ApplicationRequest request);
}
