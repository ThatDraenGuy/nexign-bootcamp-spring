package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 11, nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "TARIFF_ID", nullable = false)
    private Tariff tariff;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Report> reports;
}
