package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTOFormat {

    private Integer id;
    private String nome;
    private String cognome;
    private List<String> listaCorsi = new ArrayList<String>();

    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getId()
    {
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
    public String getCognome() {
        return cognome;
    }

    public void addCorso(String nomeCorso)
    {
        listaCorsi.add(nomeCorso);
    }
    public List<String> getListaCorsi()
    {
        return listaCorsi;
    }
    public void setListaCorsi(List<String> listaCorsi)
    {
        this.listaCorsi = listaCorsi;
    }
}
