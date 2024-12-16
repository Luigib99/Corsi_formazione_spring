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
        CorsoConverter corsoConverter = new CorsoConverter();
        List<Corso> listaCorsi = docente.getListaCorsi();
        if (listaCorsi != null)
        {
            for (Corso corso: listaCorsi)
            {
                CorsoDTO corsoDTO = corsoConverter.entityToDTO(corso);
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

    public static DocenteDTOFormat entityToDTOFormat(Docente docente)
    {
        DocenteDTOFormat docenteDTOFormat = new DocenteDTOFormat();
        docenteDTOFormat.setId(docente.getId());
        docenteDTOFormat.setNome(docente.getNome());
        docenteDTOFormat.setCognome(docente.getCognome());
        List<String> listaCorsiFormat = new ArrayList<String>();
        List<Corso> listaCorsi = docente.getListaCorsi();
        if(listaCorsi != null)
        {
            for (Corso corso: listaCorsi)
            {
                String nomeCorso = corso.getNomeCorso();
                listaCorsiFormat.add(nomeCorso);
            }
            docenteDTOFormat.setListaCorsi(listaCorsiFormat);
        }
        return docenteDTOFormat;
    }
}
