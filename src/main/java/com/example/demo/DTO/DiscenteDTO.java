package com.example.demo.DTO;
import com.example.demo.entity.Corso;

import java.util.ArrayList;
import java.util.List;

public class DiscenteDTO {
    private int id;
    private String nome;
    private String cognome;
    private String matricola;
    private String dataNascita;
    private List<Corso> listaCorso = new ArrayList<>();

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
