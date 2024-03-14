package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesaCreatedRepository extends JpaRepository<DesaCreated,Integer> {

    DesaCreated findDesaCreatedByCodeEquals(String code );

    List<DesaCreated> findAllByUserCreated_IdAndState(Integer userId, Integer state);




}
