package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;


import com.desafios.DesafiosEducativosBackend.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCards(){
        return ResponseEntity.ok().body(cardService.getCards());
    }
    @PostMapping
    public ResponseEntity<Card> saveCard(@RequestBody final Card card) throws URISyntaxException {
        if (card.getId() != null){
            throw new IllegalArgumentException("The new user shouldn't have Id ");
        }

        Card carddb = cardService.save(card);
        return ResponseEntity.created(new URI("/v1/users/"+carddb.getId())).body(carddb);
    }
}
