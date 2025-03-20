package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTOFormat;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteDTOFormat;
import com.example.demo.entity.*;
import com.example.demo.repository.DocenteRepository;

import java.util.ArrayList;
import java.util.List;

public class DocenteConverter {


    public static DocenteDTO entityToDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        List<Corso> listaCorsi = docente.getListaCorsi();
        if (listaCorsi != null)
        {
            for (Corso corso: listaCorsi)
            {
                CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso);
                docenteDTO.addCorso(corsoDTO);
            }
        }
        return docenteDTO;}

    public static Docente DTOToEntity(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        if (docenteDTO.getId()!=null)
        {
            docente.setId(docenteDTO.getId());
        }
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        List<CorsoDTO> listaCorsi = docenteDTO.getListaCorsi();
        if (listaCorsi != null)
        {
            for (CorsoDTO corsoDTO: listaCorsi)
            {
                Corso corso = CorsoConverter.DTOToEntity(corsoDTO);
                docente.addCorso(corso);
            }
        }
        return docente;
    }
}
