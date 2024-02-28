package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.repositories.DesaJoinRepository;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DesaJoinServiceImplement implements DesaJoinService {

    private final DesaJoinRepository desaJoinRepository;

    public DesaJoinServiceImplement(DesaJoinRepository desaJoinRepository) {
        this.desaJoinRepository = desaJoinRepository;
    }

    @Override
    public List<DesaJoin> getDesaJoin() {
        return desaJoinRepository.findAll();
    }

    @Override
    public List<DesaJoin> getDesaJoinById(Integer id) {
        return desaJoinRepository.getAllByUser_Id(id);
    }

    @Override
    public DesaJoin save(DesaJoin desaJoin) {
        return desaJoinRepository.save(desaJoin);
    }

    @Override
    public DesaJoin getIdDesaJoinByIdUserAndIdDesaCreated(Integer idUser, Integer idDesa) {
        return desaJoinRepository.findDesaJoinByUser_IdAndAndDesaCreated_Id(idUser,idDesa);
    }
}
