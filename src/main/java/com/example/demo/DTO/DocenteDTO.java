package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTO> listaCorsi = new ArrayList<CorsoDTO>();

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

    public void addCorso(CorsoDTO corso)
    {
        listaCorsi.add(corso);
    }
    public List<CorsoDTO> getListaCorsi()
    {
        return listaCorsi;
    }
    public void setListaCorsi(List<CorsoDTO> listaCorsi)
    {
        this.listaCorsi = listaCorsi;
    }
}
