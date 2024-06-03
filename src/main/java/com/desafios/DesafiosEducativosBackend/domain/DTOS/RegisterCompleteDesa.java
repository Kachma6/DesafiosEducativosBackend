package com.desafios.DesafiosEducativosBackend.domain.DTOS;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCompleteDesa {
    private LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
  //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime finishedDate;
    private String nameDesa;
    private String description;
    private Integer numRep;
    private User idUser;
    private String code;
    private List<Card> cards;

//    public DesaCreated getDesaCreateComplete(){
//        DesaCreated aux = new DesaCreated(
//             created,
//             finishedDate,
//             nameDesa,
//             description,
//             numRep,
//             1,
//             idUser,
//             code
//        );
//        return aux;
//    }
    public void setDesaCreated(DesaCreated desaCreated){
        this.nameDesa = desaCreated.getNameDesa();
        this.description = desaCreated.getDescription();
        this.code = desaCreated.getCode();
        this.finishedDate = desaCreated.getFinishedDate();
        this.created = desaCreated.getCreated();
        this.numRep = desaCreated.getNumRep();
    }
}

