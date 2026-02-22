package com.ats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 One Feedback belongs to one Interview
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", nullable = false, unique = true)
    private Interview interview;

    @Column(nullable = false)
    private Integer technicalRating;   // e.g., 1–5

    @Column(nullable = false)
    private Integer communicationRating;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Recommendation recommendation;

    @CreationTimestamp
    private LocalDateTime submittedAt;
}
