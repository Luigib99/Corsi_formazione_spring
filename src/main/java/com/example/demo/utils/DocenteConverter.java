package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTONoDocente;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DocenteConverter {

    public static DocenteDTO entityToDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        CorsoConverter corsoConverter = new CorsoConverter();

        //creiamo la lista dei corsi di docenteDTO se ci sono dei corsi in docente
        List<Corso> listaCorsi = docente.getListaCorsi();
        if (listaCorsi != null)
        {
            for (Corso corso: listaCorsi)
            {
                CorsoDTONoDocente corsoDTONoDocente = corsoConverter.CorsoIgnore(corso);
                docenteDTO.addCorso(corsoDTONoDocente);
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
        List<Corso> listaCorsi = new ArrayList<Corso>();
        docente.setListaCorsi(listaCorsi);
        return docente;
    }
}
