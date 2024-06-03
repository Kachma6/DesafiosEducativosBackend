package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.DesaJoinComplete;
import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterToDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import com.desafios.DesafiosEducativosBackend.services.DesaCreatedService;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import com.desafios.DesafiosEducativosBackend.services.RepDesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/inscribirse")
public class IncribirseController {
    private final DesaJoinService desaJoinService;
    private final DesaCreatedService desaCreatedService;

    private final RepDesaService repDesaService;

    public IncribirseController(DesaJoinService desaJoinService, DesaCreatedService desaCreatedService, RepDesaService repDesaService) {
        this.desaJoinService = desaJoinService;
        this.desaCreatedService = desaCreatedService;
        this.repDesaService = repDesaService;
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<List<DesaJoinComplete>> getDesaJoinByUserId(@PathVariable final Integer idUser){
        List<DesaJoin> desaJoins = desaJoinService.getDesaJoinByUserId(idUser);
        List<DesaJoinComplete> desaJoinCompletes = new ArrayList<>();
        for(int i = 0 ; i<desaJoins.size();i++){
            List<RepDesa> repDesas = repDesaService.getRepasosByIdDesaJoin(desaJoins.get(i).getId());
            DesaJoinComplete desaJoinComplete1 = new DesaJoinComplete(desaJoins.get(i).getId(),desaJoins.get(i).getDesaCreated(),desaJoins.get(i).getNumReps(),desaJoins.get(i).getUser(), repDesas);
            desaJoinCompletes.add(desaJoinComplete1);
        }
        return ResponseEntity.ok().body(desaJoinCompletes);
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
