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

    @ManyToOne
    @JoinColumn(name = "BILLING_OPERATION_ID", nullable = false)
    private BillingOperation billingOperation;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @Column(nullable = false)
    private double totalCost;

    @Column(nullable = false)
    private int totalMinutes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private List<CallSummary> records;

    @ManyToOne
    @JoinColumn(name = "MONETARY_UNIT_ID", nullable = false)
    private MonetaryUnit monetaryUnit;
}
