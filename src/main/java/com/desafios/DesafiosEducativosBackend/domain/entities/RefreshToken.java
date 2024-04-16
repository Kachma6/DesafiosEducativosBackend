package com.desafios.DesafiosEducativosBackend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date")
    private Instant expiryDate;
}
