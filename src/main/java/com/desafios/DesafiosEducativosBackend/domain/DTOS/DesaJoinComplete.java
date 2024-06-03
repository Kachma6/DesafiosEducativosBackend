package com.desafios.DesafiosEducativosBackend.domain.DTOS;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesaJoinComplete {
    private Integer id;
    private DesaCreated desaCreated;
    private Integer numReps;
    private User user;

    private List<RepDesa> repsDesa;
}
