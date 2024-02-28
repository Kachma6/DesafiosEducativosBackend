package com.desafios.DesafiosEducativosBackend.services;


import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;

import java.util.List;

public interface DesaJoinService {
    List<DesaJoin> getDesaJoin();
    List<DesaJoin> getDesaJoinById(Integer id);
    DesaJoin save(DesaJoin desaJoin);

    DesaJoin getIdDesaJoinByIdUserAndIdDesaCreated(Integer idUser, Integer idDesa);
}
