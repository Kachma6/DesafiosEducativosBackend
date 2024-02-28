package com.desafios.DesafiosEducativosBackend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rep_desa")
public class RepDesa {

    @Id
    @SequenceGenerator(name = "repaso_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "repaso_sequence")
    private Integer id;
    @Column( name = "cards_correct")
    private Integer cardsCorrect;
    @Column( name = "cards_incorrect")
    private Integer cardsIncorrect;
    @Column( name = "date_rep")
    private LocalDateTime dateRep;
    @ManyToOne
    @JoinColumn(name = "id_desa")
    private DesaJoin desaJoin;

    public RepDesa(Integer cardsCorrect, Integer cardsIncorrect, LocalDateTime dateRep, DesaJoin desaJoin) {
        this.cardsCorrect = cardsCorrect;
        this.cardsIncorrect = cardsIncorrect;
        this.dateRep = dateRep;
        this.desaJoin = desaJoin;
    }

    public RepDesa() {
    }
}
