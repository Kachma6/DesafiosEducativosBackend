package com.desafios.DesafiosEducativosBackend.services;


import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;

import java.util.List;
import java.util.Optional;

public interface DesaJoinService {
    List<DesaJoin> getDesaJoin();

    Optional<DesaJoin> getDesaJoinById(Integer id);
    List<DesaJoin> getDesaJoinByUserId(Integer id);
    DesaJoin save(DesaJoin desaJoin);

    DesaJoin getIdDesaJoinByIdUserAndIdDesaCreated(Integer idUser, Integer idDesa);

    DesaJoin putDesaJoin(DesaJoin desaJoin);
}
