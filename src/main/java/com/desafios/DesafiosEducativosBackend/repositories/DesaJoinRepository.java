package com.desafios.DesafiosEducativosBackend.repositories;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesaJoinRepository extends JpaRepository<DesaJoin,Integer> {
    List<DesaJoin> getAllByUser_Id(Integer id);
    DesaJoin findDesaJoinByUser_IdAndAndDesaCreated_Id(Integer idUser, Integer idDesa);
}
