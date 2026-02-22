package com.ats.service;

import com.ats.dto.FeedbackRequest;
import com.ats.dto.FeedbackResponse;

public interface FeedbackService {
    FeedbackResponse submitFeedback(FeedbackRequest request);
}
