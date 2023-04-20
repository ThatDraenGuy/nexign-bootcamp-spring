package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_summaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REPORT_ID", nullable = false)
    private Report report;

    @ManyToOne
    @JoinColumn(name = "CALL_TYPE_ID", nullable = false)
    private CallType callType;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private double cost;
}
