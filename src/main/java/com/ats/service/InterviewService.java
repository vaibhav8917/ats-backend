package com.ats.service;

import com.ats.dto.InterviewRequest;
import com.ats.dto.InterviewResponse;

public interface InterviewService {
    InterviewResponse scheduleInterview(InterviewRequest requestDTO);

}
