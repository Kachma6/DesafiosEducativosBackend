package com.desafios.DesafiosEducativosBackend.domain.DTOS;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String answer;
    private String question;
    private DesaCreated idDesaCreated;
   private String url;
   private Integer idImage;
}
