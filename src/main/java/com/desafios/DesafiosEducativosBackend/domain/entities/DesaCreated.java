package com.desafios.DesafiosEducativosBackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;



@Entity
@Data
@Table(name = "desa_created")
public class DesaCreated {
    @Id
    @SequenceGenerator(name = "desa_created_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "desa_created_sequence")
    private Integer id;
    private LocalDateTime created;
    @Column(name = "finished_date")
  //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    private LocalDateTime finishedDate;
    @Column(name = "name_desa")
    private String nameDesa;

    private String description;
    @Column(name = "num_rep")
    private Integer numRep;

//    state = 0   eliminado
//    state = 1   activo

    private Integer state;
    @ManyToOne
    @JoinColumn(name = "user_created")
    private User userCreated;
    private String code;


    public DesaCreated(LocalDateTime created, LocalDateTime finishedDate, String nameDesa, String description, Integer numRep, Integer state, User userCreated, String code) {
        this.created = created;
        this.finishedDate = finishedDate;
        this.nameDesa = nameDesa;
        this.description = description;
        this.numRep = numRep;
        this.state = state;
        this.userCreated = userCreated;
        this.code = code;
    }

    public DesaCreated() {
    }
}
