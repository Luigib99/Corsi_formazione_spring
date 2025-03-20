package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;

import java.util.ArrayList;
import java.util.List;

public class CorsoConverter {

    public static CorsoDTO entityToDTO(Corso corso) {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setId(corso.getId());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataCorso(corso.getDataCorso());
        corsoDTO.setDurataCorso(corso.getDurataCorso());
        Docente docente = corso.getDocente();
        if (corso.getDocente() != null) {
            corsoDTO.setDocente(DocenteConverter.entityToDTO(docente));
        }

        List<DiscenteDTO> listaDiscenteDTO = new ArrayList<>();
        if (corso.getListaDiscenti() != null) {
            for (Discente discente : corso.getListaDiscenti()) {
                DiscenteDTO discenteDTO = DiscenteConverter.entityToDTO(discente);
                listaDiscenteDTO.add(discenteDTO);
            }
        }
        corsoDTO.setListaDiscenti(listaDiscenteDTO);
        return corsoDTO;
    }

    public static Corso DTOToEntity(CorsoDTO corsoDTO) {
        Corso corso = new Corso();
        corso.setId(corsoDTO.getId());
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDataCorso(corsoDTO.getDataCorso());
        corso.setDurataCorso(corsoDTO.getDurataCorso());
        if (corsoDTO.getDocente() != null) {
            corso.setDocente(DocenteConverter.DTOToEntity(corsoDTO.getDocente()));
        }
        List<Discente> listaDiscenti = new ArrayList<>();
        if (corsoDTO.getListaDiscenti() != null) {
            for (DiscenteDTO discenteDTO : corsoDTO.getListaDiscenti()) {
                Discente discente = DiscenteConverter.DTOToEntity(discenteDTO);
                listaDiscenti.add(discente);
            }
        }
        corso.setListaDiscenti(listaDiscenti);
        return corso;
    }

    /*public static Corso FormatToEntity(CorsoDTO corsoDTO, DocenteRepository docenteRepository) {
        Corso corso = new Corso();
        corso.setId(corsoDTO.getId());
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDataCorso(corsoDTO.getDataCorso());
        corso.setDurataCorso(corsoDTO.getDurataCorso());
        Docente idDocente = corsoDTO.getDocente();
        if (idDocente != null) {
            Docente docente = docenteRepository.findById(idDocente).orElse(null);
            corso.setDocente(docente);
        }
        List<Discente> listaDiscenti = new ArrayList<>();
        if (corsoDTO.getListaDiscenti() != null) {
            for (DiscenteDTO discenteDTO : corsoDTO.getListaDiscenti()) {
                Discente discente = DiscenteConverter.DTOToEntity(discenteDTO);
                listaDiscenti.add(discente);
            }
        }
        corso.setListaDiscenti(listaDiscenti);
        return corso;
    }*/
}
