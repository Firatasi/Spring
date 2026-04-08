package com.demo.echoscan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",  nullable = false)
    private String originalText;

    @Column(nullable = false)
    private Double aiProbability;

    @Column(nullable = false)
    private Double plagiarismScore;

    @Column(nullable = false)
    private Double styleConsistencyScore;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private LocalDateTime createdAt;

}
