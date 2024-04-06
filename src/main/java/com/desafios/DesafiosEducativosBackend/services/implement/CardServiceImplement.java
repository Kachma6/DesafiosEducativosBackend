package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;
import com.desafios.DesafiosEducativosBackend.repositories.CardRepository;
import com.desafios.DesafiosEducativosBackend.services.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardServiceImplement implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImplement(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getCardsByIdDesa(Integer id) {
        return cardRepository.getCardsByIdDesaCreated_Id(id);
    }

    @Override
    public List<Card> setListCards(List<Card> cards, Integer id) {
        cardRepository.deleteAll(cardRepository.getCardsByIdDesaCreated_Id(id));
        return cardRepository.saveAll(cards);

    }
}
