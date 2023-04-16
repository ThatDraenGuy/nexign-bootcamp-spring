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
    @JoinColumn(name = "CALL_TYPE_ID")
    private CallType callType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Duration duration;

    private double cost;

    @ManyToOne
    @JoinColumn(name = "MONETARY_UNIT_ID")
    private MonetaryUnit monetaryUnit;
}
