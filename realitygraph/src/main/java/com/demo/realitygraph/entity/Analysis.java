package com.demo.realitygraph.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "analyses")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_text", nullable = false, columnDefinition = "TEXT")
    private String inputText;

    @Column(name = "reliability_score")
    private Integer reliabilityScore; //İçeriğin güvenilirlik puanı

    @Column(name = "bias_score")
    private Integer biasScore;//Taraflılık puanı

    @Column(name = "sentiment", length = 50)
    private String sentiment;//Duygu analizi sonucu: pozitif, negatif veya nötr

    @Column(name = "explanation", columnDefinition = "TEXT")
    private String explanation;//AI’ın açıklaması.

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}