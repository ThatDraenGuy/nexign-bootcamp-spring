package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Client client;

    private double totalCost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private List<Cdr> records;

    private MonetaryUnit monetaryUnit;
}
