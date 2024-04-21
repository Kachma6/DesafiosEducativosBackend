package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.entities.Card;


import com.desafios.DesafiosEducativosBackend.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{idDesaCreated}")
    public ResponseEntity<List<Card>> getCardsByIdDesa(@PathVariable final Integer idDesaCreated){
        return ResponseEntity.ok().body(cardService.getCardsByIdDesa(idDesaCreated));
    }


}
