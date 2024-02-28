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
@RequestMapping("v1/created-desa")
public class DesafioCreatedController {
    private final DesaCreatedService desaCreatedService;
    private final CardService cardService;

    public DesafioCreatedController(DesaCreatedService desaCreatedService, CardService cardService) {
        this.desaCreatedService = desaCreatedService;
        this.cardService = cardService;
    }
    @GetMapping("/{code}")
    public ResponseEntity<DesaCreated> getDesaCreatedByCode( @PathVariable final String code){
        return ResponseEntity.ok().body(desaCreatedService.findByCode(code));
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<DesaCreated>> getDesaCreatedByCode(@PathVariable final Integer id){
        return ResponseEntity.ok().body(desaCreatedService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<DesaCreated>> getDesaCreated(){
        return ResponseEntity.ok().body(desaCreatedService.getDesaCreated());
    }
    @PostMapping( )
    public ResponseEntity<DesaCreated> saveDesaCreated(@RequestBody final RegisterCompleteDesa registerCompleteDesa) throws URISyntaxException {

//        if (registerCompleteDesa.getId() != null){
//            throw new IllegalArgumentException("The new user shouldn't have Id ");
//        }
//        desaCreated.setFinishedDate(LocalDateTime.now());
        System.out.println(registerCompleteDesa);
        registerCompleteDesa.setCreated(LocalDateTime.now());
        System.out.println(registerCompleteDesa);
        DesaCreated desa = new DesaCreated();
        desa.setNameDesa(registerCompleteDesa.getNameDesa());
        desa.setFinishedDate(registerCompleteDesa.getFinishedDate());
        desa.setCode(registerCompleteDesa.getCode());
        desa.setNumRep(registerCompleteDesa.getNumRep());
        desa.setCreated(LocalDateTime.now());

        desa.setUserCreated(registerCompleteDesa.getIdUser());
        desa.setDescription(registerCompleteDesa.getDescription());
        DesaCreated registerdb = desaCreatedService.save(desa);
        List<Card> cards = registerCompleteDesa.getCards();
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).getIdDesaCreated().setId(registerdb.getId());
            cardService.save( new Card(
                    cards.get(i).getQuestion(),
                    cards.get(i).getAnswer(),
                    cards.get(i).getIdDesaCreated()
            ));
        }
        System.out.println(registerdb);
        return ResponseEntity.created(new URI("/v1/users/"+registerdb.getId())).body(registerdb);
    }
}
