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
public class DiscenteDTOFormat {
    private int id;
    private String nome;
    private String cognome;
    private String matricola;
    private Date dataNascita;
    private List <String> listaNomeCorsi = new ArrayList<>();


    public void addNomeCorso(String nomeCorso)
    {
        listaNomeCorsi.add(nomeCorso);
    }

}
