package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DocenteDTOFormat {


    private Integer id;
    private String nome;
    private String cognome;
    private List<String> listaCorsi = new ArrayList<String>();

    public void addCorso(String nomeCorso)
    {
        listaCorsi.add(nomeCorso);
    }
}
