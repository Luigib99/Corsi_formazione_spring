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
public class DocenteDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private List<CorsoDTO> listaCorsi = new ArrayList<CorsoDTO>();

    public void addCorso(CorsoDTO corso)
    {
        listaCorsi.add(corso);
    }
    public void deleteCorso(CorsoDTO corso)
    {
        listaCorsi.remove(corso);
    }
}
