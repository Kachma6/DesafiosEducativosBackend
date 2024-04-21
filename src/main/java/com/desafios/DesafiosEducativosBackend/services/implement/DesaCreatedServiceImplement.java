package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterCompleteDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.Card;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.desafios.DesafiosEducativosBackend.repositories.CardRepository;
import com.desafios.DesafiosEducativosBackend.repositories.DesaCreatedRepository;


import com.desafios.DesafiosEducativosBackend.services.DesaCreatedService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Optional<DesaCreated> findDesaCreatedById(Integer id) {
        return Optional.empty();
    }

    @Override
    public DesaCreated findByCode(String code) {
        return desaCreatedRepository.findDesaCreatedByCodeEquals(code);
    }

    @Override
    public DesaCreated save(RegisterCompleteDesa registerCompleteDesa) {
        registerCompleteDesa.setCreated(LocalDateTime.now());
        DesaCreated desa = new DesaCreated();
        desa.setNameDesa(registerCompleteDesa.getNameDesa());
        desa.setFinishedDate(registerCompleteDesa.getFinishedDate());
        desa.setCode(registerCompleteDesa.getCode());
        desa.setNumRep(registerCompleteDesa.getNumRep());
        desa.setState(1);
        desa.setNumCards(registerCompleteDesa.getCards().size());
        desa.setNumMembers(0);
        desa.setCreated(LocalDateTime.now());
        desa.setUserCreated(registerCompleteDesa.getIdUser());
        desa.setDescription(registerCompleteDesa.getDescription());

        DesaCreated db = desaCreatedRepository.save(desa);

        List<Card> cards = registerCompleteDesa.getCards();
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).getIdDesaCreated().setId(db.getId());
            cardRepository.save( new Card(
                    cards.get(i).getQuestion(),
                    cards.get(i).getAnswer(),
                    cards.get(i).getIdDesaCreated()
            ));
        }

        return db ;

    }

    @Override
    public List<DesaCreated> getDesaCreatedByUserId(Integer userId) {
        return desaCreatedRepository.findAllByUserCreated_IdAndState(userId , 1);
    }

    @Override
    public void delete(Integer id) {
        Optional<DesaCreated> desa = desaCreatedRepository.findById(id);
        if(desa.isPresent()){
            desa.get().setState(0);
            desaCreatedRepository.save(desa.get());

        }
    }
}
