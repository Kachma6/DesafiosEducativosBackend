package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterToDesa;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaCreated;
import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.services.DesaCreatedService;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/inscribirse")
public class DesaJoinController {
    private final DesaJoinService desaJoinService;
    private final DesaCreatedService desaCreatedService;

    public DesaJoinController(DesaJoinService desaJoinService, DesaCreatedService desaCreatedService) {
        this.desaJoinService = desaJoinService;
        this.desaCreatedService = desaCreatedService;
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<List<DesaJoin>> getDesaJoinByUserId(@PathVariable final Integer idUser){
        return ResponseEntity.ok().body(desaJoinService.getDesaJoinByUserId(idUser));
    }
    @GetMapping
    public ResponseEntity<List<DesaJoin>> getDesaCreated(){
        return ResponseEntity.ok().body(desaJoinService.getDesaJoin());
    }
    @GetMapping("/{user}/{desa}")
    public ResponseEntity<DesaJoin> getDesaCreatedbyIdUserAndIdDesa(@PathVariable final Integer user, @PathVariable final Integer desa){
        return ResponseEntity.ok().body(desaJoinService.getIdDesaJoinByIdUserAndIdDesaCreated(user,desa));
    }

//    @PostMapping
//    public ResponseEntity<DesaJoin> saveUser(@RequestBody final DesaJoin desaJoin) throws URISyntaxException {
//        if (desaJoin.getId() != null){
//            throw new IllegalArgumentException("The new user shouldn't have Id ");
//        }
//
//        DesaJoin db = desaJoinService.save(desaJoin);
//        return ResponseEntity.created(new URI("/v1/users/"+db.getId())).body(db);
//    }
@PostMapping
public ResponseEntity<DesaJoin> inscribirse(@RequestBody final RegisterToDesa registerToDesa) throws URISyntaxException {
//    if (desaJoin.getId() != null){
//        throw new IllegalArgumentException("The new user shouldn't have Id ");
//    }
    DesaCreated desaCreated = desaCreatedService.findByCode(registerToDesa.getCode());
    if(desaCreated != null){
        System.out.println(desaCreated);
        DesaJoin desaJoin = new DesaJoin();
        desaJoin.setDesaCreated(desaCreated);
        desaJoin.setUser(registerToDesa.getUser());
        desaJoin.setNumReps(0);
        DesaJoin db = desaJoinService.save(desaJoin);
        return ResponseEntity.created(new URI("/v1/users/"+db.getId())).body(db);
    }else{
        throw new IllegalArgumentException("No se Encontro el desafio");
    }


}
 @PutMapping("/{id}/edit-rep")
 public ResponseEntity<DesaJoin> putDesaJoin(@PathVariable final Integer id ) throws URISyntaxException {
     Optional<DesaJoin> desa = desaJoinService.getDesaJoinById(id);
     if(desa.isPresent()){
         desa.get().setNumReps(desa.get().getNumReps()+1);
         desaJoinService.save(desa.get());
     }


    return ResponseEntity.ok(desa.get());


 }
}
