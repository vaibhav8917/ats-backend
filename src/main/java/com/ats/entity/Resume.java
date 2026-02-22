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
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String filePath;

    private String fileType;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    // 🔹 One Resume belongs to one Application
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private Application application;

    @PrePersist
    protected void onUpload() {
        this.uploadedAt = LocalDateTime.now();
    }
}
