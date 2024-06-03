package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterToDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import com.desafios.DesafiosEducativosBackend.repositories.DesaCreatedRepository;
import com.desafios.DesafiosEducativosBackend.repositories.DesaJoinRepository;
import com.desafios.DesafiosEducativosBackend.repositories.RepDesaRepository;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Integer inscribirse(RegisterToDesa registerToDesa) {

        DesaCreated desaCreated = desaCreatedRepository.findDesaCreatedByCodeEquals(registerToDesa.getCode());
        DesaJoin desaJoin1 = desaJoinRepository.findDesaJoinByUser_IdAndAndDesaCreated_Id(registerToDesa.getUser().getId(), desaCreated.getId());
        if(desaJoin1 == null){
            if(desaCreated != null){
                DesaJoin desaJoin = new DesaJoin();
                desaJoin.setDesaCreated(desaCreated);
                desaJoin.setUser(registerToDesa.getUser());
                desaJoin.setNumReps(0);
                desaJoin.setState(0);
                DesaJoin db = desaJoinRepository.save(desaJoin);
                desaCreated.setNumMembers(desaCreated.getNumMembers()+1);
                desaCreatedRepository.save(desaCreated);
                return db.getId();
            }
        }

        return -1;
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

    @Override
    public Integer getScore(Integer idUser) {
        List<DesaJoin> listaDesaJoin = desaJoinRepository.getAllByUser_Id(idUser);
        //Terminado = 1
        Integer option1 = 0;

        for(int i = 0; i < listaDesaJoin.size();i++){
            if (listaDesaJoin.get(i).getState() == 1){
                option1++;
            }

        }
        return option1 * 5;
    }
}
