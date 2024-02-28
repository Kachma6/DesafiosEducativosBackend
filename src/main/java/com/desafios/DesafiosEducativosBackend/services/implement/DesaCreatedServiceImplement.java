package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterCompleteDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.Card;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.desafios.DesafiosEducativosBackend.repositories.CardRepository;
import com.desafios.DesafiosEducativosBackend.repositories.DesaCreatedRepository;


import com.desafios.DesafiosEducativosBackend.services.DesaCreatedService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesaCreatedServiceImplement implements DesaCreatedService {
    private final DesaCreatedRepository desaCreatedRepository;
    private final CardRepository cardRepository;

    public DesaCreatedServiceImplement(DesaCreatedRepository desaCreatedRepository, CardRepository cardRepository) {
        this.desaCreatedRepository = desaCreatedRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<DesaCreated> getDesaCreated() {
        return desaCreatedRepository.findAll() ;
    }

    @Override
    public Optional<DesaCreated> findById(Integer id) {
        return desaCreatedRepository.findById(id);
    }

    @Override
    public DesaCreated findByCode(String code) {
        return desaCreatedRepository.findDesaCreatedByCodeEquals(code);
    }

    @Override
    public DesaCreated save(DesaCreated desaCreated) {


//        DesaCreated desa = new DesaCreated(registerCompleteDesa.getCreated(),
//                registerCompleteDesa.getFinishedDate(),
//                registerCompleteDesa.getNameDesa(),
//                registerCompleteDesa.getDescription(),
//                registerCompleteDesa.getNumRep(),
//                registerCompleteDesa.getUser(),
//                registerCompleteDesa.getCode());
//        DesaCreated dbdesa = desaCreatedRepository.save(desa);

//        List<Card> cards = registerCompleteDesa.getCards();
//        for(int i = 0; i<cards.size();i++){
//            Card card = cards.get(i);
//            DesaCreated desaCreated = new DesaCreated();
//            desaCreated.setId(dbdesa.getId());
//            card.setIdDesaCreated(desaCreated);
//            cardRepository.save(card);
//        }
        return desaCreatedRepository.save(desaCreated);
    }
}
