package com.draen.security.data.user.entity;

import com.draen.domain.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "client_id", unique = true, nullable = false, updatable = false)
    private Client client;

    private String password;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "userRoles")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles;

}
