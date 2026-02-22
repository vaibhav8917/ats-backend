package com.ats.repository;

import com.ats.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);
}
