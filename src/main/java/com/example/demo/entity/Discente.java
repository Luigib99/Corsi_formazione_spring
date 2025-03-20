package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "discente_test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;

    @Column (nullable = false)
    private String cognome;

    @Column (nullable = false)
    private String matricola;

    @Column (name = "data_nascita" , nullable = false)
    private Date dataNascita;

    @ManyToMany (mappedBy = "listaDiscenti")
    private List <Corso> listaCorsi;

    public void addCorso(Corso corso) {listaCorsi.add(corso);}
    public void removeCorso(Corso corso) {listaCorsi.remove(corso);}
}

