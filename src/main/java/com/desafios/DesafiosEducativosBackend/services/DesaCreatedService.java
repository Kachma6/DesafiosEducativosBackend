package com.desafios.DesafiosEducativosBackend.services;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterCompleteDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;

import java.util.List;
import java.util.Optional;

public interface DesaCreatedService {
    List<DesaCreated> getDesaCreated();
    Optional<DesaCreated> findById(Integer id);
    Optional<DesaCreated> findDesaCreatedById(Integer id);
    DesaCreated findByCode(String code);
    DesaCreated save(DesaCreated desaCreated);

    List<DesaCreated>  getDesaCreatedByUserId( Integer userId);
}
