package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.services.DesaJoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/score")
public class Score {
    private final DesaJoinService desaJoinService;

    public Score(DesaJoinService desaJoinService) {
        this.desaJoinService = desaJoinService;
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<Integer> getScore(@PathVariable final Integer idUser){
        return ResponseEntity.ok().body(desaJoinService.getScore(idUser));
    }
}
