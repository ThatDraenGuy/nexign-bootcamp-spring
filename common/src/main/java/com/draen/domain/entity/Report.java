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
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    private double totalCost;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "report_cdrs",
            joinColumns =
                    @JoinColumn(name = "REPORT_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
                    @JoinColumn(name = "CDR_ID", referencedColumnName = "ID")
    )
    private List<Cdr> records;

    @ManyToOne
    @JoinColumn(name = "MONETARY_UNIT_ID")
    private MonetaryUnit monetaryUnit;
}
