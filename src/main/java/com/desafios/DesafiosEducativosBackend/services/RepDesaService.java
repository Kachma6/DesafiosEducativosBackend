package com.desafios.DesafiosEducativosBackend.services;

import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;

import java.util.List;

public interface RepDesaService {

    List<RepDesa> getRepasosByIdDesaJoin(Integer idDesaJoin) ;
    RepDesa save(RepDesa repDesa);
}
