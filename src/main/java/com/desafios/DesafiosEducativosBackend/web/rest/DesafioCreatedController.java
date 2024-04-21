package com.desafios.DesafiosEducativosBackend.web.rest;



import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterCompleteDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import com.desafios.DesafiosEducativosBackend.domain.entities.Card;

import com.desafios.DesafiosEducativosBackend.services.CardService;
import com.desafios.DesafiosEducativosBackend.services.DesaCreatedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/created-desa")
public class DesafioCreatedController {
    private final DesaCreatedService desaCreatedService;
    private final CardService cardService;

    public DesafioCreatedController(DesaCreatedService desaCreatedService, CardService cardService) {
        this.desaCreatedService = desaCreatedService;
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DesaCreated>> getDesaCreatedById(@PathVariable final Integer id){
        return ResponseEntity.ok().body(desaCreatedService.findById(id));
    }
    @GetMapping("/{id}/complete")
    public ResponseEntity<RegisterCompleteDesa> getDesaCreatedByIdComplete(@PathVariable final Integer id){
       Optional<DesaCreated> desa = desaCreatedService.findById(id);
        List<Card> cards = cardService.getCardsByIdDesa(id);
        if(desa.isPresent()){
            RegisterCompleteDesa response = new RegisterCompleteDesa(
                    desa.get().getCreated(),
                    desa.get().getFinishedDate(),
                    desa.get().getNameDesa(),
                    desa.get().getDescription(),
                    desa.get().getNumRep(),
                    desa.get().getUserCreated(),
                    desa.get().getCode(),
                    cards
            );
            return ResponseEntity.ok().body(response);
        }else{
            throw new IllegalArgumentException("Doesn't exist that desafio");
        }


    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DesaCreated>> getDesaCreated(@PathVariable Integer userId){
        return ResponseEntity.ok().body(desaCreatedService.getDesaCreatedByUserId(userId));
    }

    @PostMapping( )
    public ResponseEntity<DesaCreated> saveDesaCreated(@RequestBody final RegisterCompleteDesa registerCompleteDesa) throws URISyntaxException {
        DesaCreated registerdb = desaCreatedService.save(registerCompleteDesa);
        return ResponseEntity.ok(registerdb);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<RegisterCompleteDesa> editarDesaCreated(@PathVariable final Integer id, @RequestBody final RegisterCompleteDesa registerCompleteDesa){
//        return desaCreatedService.findById(id).map(desa -> {
//                desa.setNameDesa(registerCompleteDesa.getNameDesa());
//                desa.setDescription(registerCompleteDesa.getDescription());
//                desa.setNumRep(registerCompleteDesa.getNumRep());
//                desa.setFinishedDate(registerCompleteDesa.getFinishedDate());
//                registerCompleteDesa.setCards(cardService.setListCards(registerCompleteDesa.getCards(), id));
//                registerCompleteDesa.setDesaCreated( desaCreatedService.save(desa));
//            return ResponseEntity.ok(registerCompleteDesa);
//        }).orElseGet(()->{
//            throw new IllegalArgumentException("Don't find that desafio for edit");
//        });
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable final Integer id){
        desaCreatedService.delete(id);
        return ResponseEntity.ok().build();
    }
}
