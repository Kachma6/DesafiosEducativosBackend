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
    private String url;
    @Column(name = "id_image")
    private Integer idImage;
    @ManyToOne
    @JoinColumn(name = "id_desa_created")
    private DesaCreated idDesaCreated;


    public Card(String question, String answer, String url, Integer idImage, DesaCreated idDesaCreated) {
        this.question = question;
        this.answer = answer;
        this.url = url;
        this.idImage = idImage;
        this.idDesaCreated = idDesaCreated;
    }

    public Card(Integer id){

    }
    public Card() {
    }
}
