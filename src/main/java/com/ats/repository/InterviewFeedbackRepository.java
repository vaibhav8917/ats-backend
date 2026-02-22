package com.ats.repository;

import com.ats.entity.InterviewFeedback;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewFeedbackRepository extends JpaRepository<InterviewFeedback, Long> {
    boolean existsByInterviewId(@NotNull Long interviewId);
}
