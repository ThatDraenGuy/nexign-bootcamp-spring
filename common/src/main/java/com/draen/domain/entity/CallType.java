package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "call_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String code;

    private String name;
}
