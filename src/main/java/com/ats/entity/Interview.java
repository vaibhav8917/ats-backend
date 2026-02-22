package com.ats.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 One Interview belongs to one Application
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private Application application;

    // 🔹 Interview assigned to one Interviewer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewer_id", nullable = false)
    private User interviewer;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterviewStatus status = InterviewStatus.SCHEDULED;
}
