package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscenteDTO {
    private int id;
    private String nome;
    private String cognome;
    private String matricola;
    private Date dataNascita;
    private List<CorsoDTO> listaCorsi = new ArrayList<>();

    public void addCorso(CorsoDTO corsoDTO) {listaCorsi.add(corsoDTO);}
    public void removeCorso(CorsoDTO corsoDTO) {listaCorsi.remove(corsoDTO);}
}
