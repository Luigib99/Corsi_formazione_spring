package com.example.demo.utils;
import com.example.demo.DTO.*;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscenteConverter {

    public static DiscenteDTO entityToDTO(Discente discente)
    {
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataNascita(discente.getDataNascita());
        List <CorsoDTO> listaCorsiDTO = new ArrayList<>();
        if (discente.getListaCorsi() != null){
            for (Corso corso : discente.getListaCorsi()) {
                CorsoDTO corsoDTO =CorsoConverter.entityToDTO(corso);
                listaCorsiDTO.add(corsoDTO);
            }
        }
        discenteDTO.setListaCorsi(listaCorsiDTO);
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
        if(discenteDTO.getListaCorsi()!=null)
        {
            List<CorsoDTO> listaCorsiDTO = discenteDTO.getListaCorsi();
            for(CorsoDTO corsoDTO : listaCorsiDTO){
                Corso corso = CorsoConverter.DTOToEntity(corsoDTO);
                listaCorsi.add(corso);
            }
        }
        discente.setListaCorsi(listaCorsi);
        return discente;
    }
}
