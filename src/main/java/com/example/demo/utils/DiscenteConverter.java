package com.example.demo.utils;
import com.example.demo.DTO.*;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscenteConverter {
    public static DiscenteDTONoCorso DiscenteIgnore(Discente discente)
    {
        DiscenteDTONoCorso discenteDTONoCorso = new DiscenteDTONoCorso();
        discenteDTONoCorso.setId(discente.getId());
        discenteDTONoCorso.setNome(discente.getNome());
        discenteDTONoCorso.setCognome(discente.getCognome());
        discenteDTONoCorso.setMatricola(discente.getMatricola());
        discenteDTONoCorso.setDataNascita(discente.getDataNascita());
        List<Corso> listaCorsi = discente.getListaCorso();
        for(Corso corso : listaCorsi)
        {
            discenteDTONoCorso.addNomeCorso(corso.getNomeCorso());
        }
        return discenteDTONoCorso;
    };

    public static DiscenteDTO entityToDTO(Discente discente)
    {
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataNascita(discente.getDataNascita());
        List<Corso> listaCorsi = discente.getListaCorso();
        discenteDTO.setListaCorso(listaCorsi);
        return discenteDTO;
    };

    public static Discente DTOToEntity(DiscenteDTO discenteDTO, CorsoRepository corsoRepository, boolean update)
    {
        Discente discente = new Discente();
        discente.setId(discenteDTO.getId());
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setMatricola(discenteDTO.getMatricola());
        discente.setDataNascita(discenteDTO.getDataNascita());
        if(update)
        {
            List<Corso> listaCorsiDTO = discenteDTO.getListaCorso();
            discente.setListaCorso(listaCorsiDTO);
        }
        else
        {
            discente.setListaCorso(new ArrayList<>());
        }
        return discente;
    }
}
