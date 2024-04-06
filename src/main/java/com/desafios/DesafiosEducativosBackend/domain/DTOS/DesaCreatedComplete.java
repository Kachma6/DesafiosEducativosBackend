package com.desafios.DesafiosEducativosBackend.domain.DTOS;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesaCreatedComplete {
    private LocalDateTime created;
    private LocalDateTime finishedDate;
    private String nameDesa;
    private String description;
    private Integer numRep;
    private User idUser;
    private String code;
    private Integer numMembers;
    private Integer numCards;
    private List<Card> cards;
}
