package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesaCreatedRepository extends JpaRepository<DesaCreated,Integer> {
    DesaCreated findDesaCreatedByCode(String code);
    DesaCreated findDesaCreatedByCodeEquals(String code );
}
