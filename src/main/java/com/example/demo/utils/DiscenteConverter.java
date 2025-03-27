package com.example.demo.utils;
import com.example.demo.DTO.*;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscenteConverter {
    public static DiscenteDTOFormat DiscenteIgnore(Discente discente)
    {
        DiscenteDTOFormat discenteDTOFormat = new DiscenteDTOFormat();
        discenteDTOFormat.setId(discente.getId());
        discenteDTOFormat.setNome(discente.getNome());
        discenteDTOFormat.setCognome(discente.getCognome());
        discenteDTOFormat.setMatricola(discente.getMatricola());
        discenteDTOFormat.setDataNascita(discente.getDataNascita());
        List<Corso> listaCorsi = discente.getListaCorsi();
        for(Corso corso : listaCorsi)
        {
            discenteDTOFormat.addNomeCorso(corso.getNomeCorso());
        }
        return discenteDTOFormat;
    };

    public static DiscenteDTO entityToDTO(Discente discente)
    {
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataNascita(discente.getDataNascita());
        List<Corso> listaCorsi = discente.getListaCorsi();
        for(Corso corso : listaCorsi)
        {
            discenteDTO.addCorso(CorsoConverter.entityToDTO(corso));
        }
        return discenteDTO;
    };

    public static Discente DTOToEntity(DiscenteDTO discenteDTO)
    {
        Discente discente = new Discente();
        discente.setId(discenteDTO.getId());
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setMatricola(discenteDTO.getMatricola());
        discente.setDataNascita(discenteDTO.getDataNascita());
        List<Corso>listaCorsi = new ArrayList<>();
        if(discente.getListaCorsi() != null)
        {
            for(CorsoDTO corsoDTO : discenteDTO.getListaCorsi()){
                listaCorsi.add(CorsoConverter.DTOToEntity(corsoDTO));
            }

            discente.setListaCorsi(listaCorsi);
        }
        else
        {
            discente.setListaCorsi(new ArrayList<>());
        }
        return discente;
    }
}