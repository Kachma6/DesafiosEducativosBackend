package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import com.desafios.DesafiosEducativosBackend.repositories.DesaJoinRepository;
import com.desafios.DesafiosEducativosBackend.repositories.RepDesaRepository;
import com.desafios.DesafiosEducativosBackend.services.RepDesaService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RepDesaServiceImplement implements RepDesaService {
    private final RepDesaRepository repDesaRepository;


    public RepDesaServiceImplement(RepDesaRepository repDesaRepository) {
        this.repDesaRepository = repDesaRepository;

    }


    @Override
    public List<RepDesa> getRepasosByIdDesaJoin(Integer idDesaJoin) {

        return repDesaRepository.findAllByDesaJoin_Id(idDesaJoin) ;
    }

    @Override
    public RepDesa save(RepDesa repDesa) {
        return repDesaRepository.save(repDesa);
    }
}
