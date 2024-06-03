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
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCompleteDesa {
    private LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime finishedDate;
    private String nameDesa;
    private String description;
    private Integer numRep;
    private User idUser;
    private String code;
    private List<CardDTO> cards;
}
