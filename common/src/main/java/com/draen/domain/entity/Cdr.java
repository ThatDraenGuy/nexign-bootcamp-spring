package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "cdrs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cdr {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CALL_TYPE_ID")
    private CallType callType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String duration;

    private double cost;

}
