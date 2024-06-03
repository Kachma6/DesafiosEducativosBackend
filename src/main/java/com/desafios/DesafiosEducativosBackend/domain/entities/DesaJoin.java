package com.desafios.DesafiosEducativosBackend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "desa_join")
public class DesaJoin {
    @Id
    @SequenceGenerator(name = "desa_join_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "desa_join_sequence")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_desa_created")
    private DesaCreated desaCreated;
    @JoinColumn(name = "num_reps")
    private Integer numReps;
    private Integer state;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public DesaJoin() {
    }

    public DesaJoin(DesaCreated desaCreated, User user) {
        this.desaCreated = desaCreated;
        this.user = user;
    }
}
