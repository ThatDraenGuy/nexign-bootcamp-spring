package com.draen.domain.entity;

import com.draen.domain.entity.MonetaryUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    private int money;

    @ManyToOne
    @JoinColumn(name = "MONETARY_UNIT_ID")
    private MonetaryUnit monetaryUnit;
}
