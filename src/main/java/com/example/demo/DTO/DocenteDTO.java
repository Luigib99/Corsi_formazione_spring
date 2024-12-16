package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTONoDocente> listaCorsi = new ArrayList<CorsoDTONoDocente>();

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

    public void addCorso(CorsoDTONoDocente corso)
    {
        listaCorsi.add(corso);
    }
    public List<CorsoDTONoDocente> getListaCorsi()
    {
        return listaCorsi;
    }
    public void setListaCorsi(List<CorsoDTONoDocente> listaCorsi)
    {
        this.listaCorsi = listaCorsi;
    }
}
