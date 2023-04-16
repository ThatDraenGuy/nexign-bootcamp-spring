package com.draen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String phoneNumber;

    private int balance;

    @ManyToOne
    @JoinColumn(name = "TARIFF_ID")
    private Tariff tariff;
}
