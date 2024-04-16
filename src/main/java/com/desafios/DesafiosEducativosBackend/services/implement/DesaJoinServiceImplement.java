package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import com.desafios.DesafiosEducativosBackend.repositories.DesaCreatedRepository;
import com.desafios.DesafiosEducativosBackend.repositories.DesaJoinRepository;
import com.desafios.DesafiosEducativosBackend.repositories.RepDesaRepository;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesaJoinServiceImplement implements DesaJoinService {

    private final DesaJoinRepository desaJoinRepository;
    private final DesaCreatedRepository desaCreatedRepository;
    private final RepDesaRepository repDesaRepository;

    public DesaJoinServiceImplement(DesaJoinRepository desaJoinRepository, DesaCreatedRepository desaCreatedRepository, RepDesaRepository repDesaRepository) {
        this.desaJoinRepository = desaJoinRepository;
        this.desaCreatedRepository = desaCreatedRepository;
        this.repDesaRepository = repDesaRepository;
    }

    @Override
    public List<DesaJoin> getDesaJoin() {
        return desaJoinRepository.findAll();
    }

    @Override
    public Optional<DesaJoin> getDesaJoinById(Integer id) {
        return desaJoinRepository.findById(id);
    }

    @Override
    public List<DesaJoin> getDesaJoinByUserId(Integer id) {
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

    @Override
    public DesaJoin putDesaJoin(DesaJoin desaJoin) {
        return desaJoinRepository.save(desaJoin);
    }

    @Override
    public void desescribirse(Integer id) {
       Optional<DesaJoin> desaJoin = desaJoinRepository.findById(id);
       if(desaJoin.isPresent()){
           Optional<DesaCreated> desaCreated = desaCreatedRepository.findById(desaJoin.get().getDesaCreated().getId());
           desaCreated.get().setNumMembers(desaCreated.get().getNumMembers()-1);
           desaCreatedRepository.save( desaCreated.get());
           List<RepDesa> repDesas = repDesaRepository.findAllByDesaJoin_Id(id);
           repDesaRepository.deleteAll(repDesas);
       }

        desaJoinRepository.deleteById(id);
    }
}
