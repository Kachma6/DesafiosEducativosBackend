package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterToDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.services.DesaCreatedService;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/inscribirse")
public class IncribirseController {
    private final DesaJoinService desaJoinService;
    private final DesaCreatedService desaCreatedService;


    public IncribirseController(DesaJoinService desaJoinService, DesaCreatedService desaCreatedService) {
        this.desaJoinService = desaJoinService;
        this.desaCreatedService = desaCreatedService;
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<List<DesaJoin>> getDesaJoinByUserId(@PathVariable final Integer idUser){
        return ResponseEntity.ok().body(desaJoinService.getDesaJoinByUserId(idUser));
    }
    @PostMapping
    public ResponseEntity<DesaJoin> inscribirse(@RequestBody final RegisterToDesa registerToDesa) throws URISyntaxException {

       Integer id = desaJoinService.inscribirse(registerToDesa);
        if(id > -1  ){
           return ResponseEntity.ok().body(desaJoinService.getDesaJoinById(id).get());
        }else{
            return  ResponseEntity.status(HttpStatus.CONFLICT).body( new DesaJoin());
        }



    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desescribirse(@PathVariable final Integer id){
        desaJoinService.desescribirse(id);
        return  ResponseEntity.ok().build();
    }
}
