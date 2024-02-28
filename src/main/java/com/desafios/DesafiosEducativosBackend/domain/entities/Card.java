package com.desafios.DesafiosEducativosBackend.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "card")
public class Card {
    @Id
    @SequenceGenerator(name = "card_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "card_sequence")
    private Integer id;
    private String question;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "id_desa_created")
    private DesaCreated idDesaCreated;


    public Card(String question, String answer, DesaCreated idDesafioCreated) {
        this.question = question;
        this.answer = answer;
        this.idDesaCreated = idDesafioCreated;
    }

    public Card() {
    }
}
