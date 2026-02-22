package com.ats.repository;

import com.ats.entity.Interview;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    boolean existsByApplicationId(@NotNull Long applicationId);
}
