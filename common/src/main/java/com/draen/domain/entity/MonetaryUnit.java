package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monetary_units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonetaryUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String code;

    private String name;
}
