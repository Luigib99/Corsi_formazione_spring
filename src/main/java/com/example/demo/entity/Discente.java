package com.example.demo.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discente_test")

public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cognome;
    private String matricola;
    @Column (name = "data_nascita")
    private String dataNascita;
    @ManyToMany (mappedBy = "listaDiscenti")
    private List <Corso> listaCorso;

    public void setId(int id) {this.id = id;}
    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCognome() {return cognome;}

    public void setMatricola (String matricola) {this.matricola = matricola;}
    public String getMatricola() {return matricola;}

    public void setDataNascita(String dataNascita) {this.dataNascita = dataNascita;}
    public String getDataNascita() {return dataNascita;}

    public List getListaCorso() {return listaCorso;}
    public void addCorso(Corso corso) {listaCorso.add(corso);}
    public void setListaCorso (List<Corso> listaCorso) {this.listaCorso = listaCorso;}
    public void removeCorso(Corso corso) {listaCorso.remove(corso);}
}

