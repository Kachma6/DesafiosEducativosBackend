package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.entities.DesaJoin;
import com.desafios.DesafiosEducativosBackend.domain.entities.RepDesa;
import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import com.desafios.DesafiosEducativosBackend.services.RepDesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/rep-desa")
public class RepDesaController {
    private final RepDesaService repDesaService;
    private final DesaJoinService desaJoinService;

    public RepDesaController(RepDesaService repDesaService, DesaJoinService desaJoinService) {
        this.repDesaService = repDesaService;
        this.desaJoinService = desaJoinService;
    }

    @PostMapping
    public ResponseEntity<RepDesa> saveRepaso(@RequestBody final RepDesa repDesa) throws URISyntaxException {
        if (repDesa.getId() != null){
            throw new IllegalArgumentException("The new user shouldn't have Id ");
        }
        Optional<DesaJoin> desa = desaJoinService.getDesaJoinById(repDesa.getDesaJoin().getId());
        if(desa.isPresent()){
            desa.get().setNumReps(desa.get().getNumReps()+1);
        }

        repDesa.setDateRep(LocalDateTime.now());
        RepDesa repDesadb = repDesaService.save(repDesa);

        if ( desa.get().getNumReps() == desa.get().getDesaCreated().getNumRep() ){
            desa.get().setState(1);
            DesaJoin db = desaJoinService.save(desa.get());
        }
        return ResponseEntity.created(new URI("/v1/users/"+repDesadb.getId())).body(repDesadb);
    }
}
