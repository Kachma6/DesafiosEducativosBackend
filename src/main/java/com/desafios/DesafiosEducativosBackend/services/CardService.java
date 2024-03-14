package com.desafios.DesafiosEducativosBackend.services;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;

import java.util.List;

public interface CardService {
    List<Card> getCards();
    Card save(Card card);

    List<Card> getCardsByIdDesa(Integer id);

    List<Card> setListCards(List<Card> cards);
}
