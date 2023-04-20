package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "billing_operations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Integer operationNumber;

    @Column(nullable = false)
    private LocalDateTime operationDateTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billingOperation")
    private List<Report> reports;
}
