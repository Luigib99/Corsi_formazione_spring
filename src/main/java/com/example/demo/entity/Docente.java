package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "docente_test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String cognome;

    @OneToMany (mappedBy = "docente",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Corso> listaCorsi;


    public void removeCorso (Corso corso)
    {
        listaCorsi.remove(corso);
    }
    public void addCorso(Corso corso)
    {
        listaCorsi.add(corso);
    }
}

