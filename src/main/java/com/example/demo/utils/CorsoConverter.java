package com.example.demo.utils;
import com.example.demo.DTO.*;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CorsoConverter {
    public static CorsoDTONoDocente CorsoIgnore(Corso corso)
    {
        CorsoDTONoDocente corsoDTONoDocente = new CorsoDTONoDocente();
        corsoDTONoDocente.setNomeCorso(corso.getNomeCorso());
        corsoDTONoDocente.setId(corso.getId());
        corsoDTONoDocente.setDataCorso(corso.getDataCorso());
        corsoDTONoDocente.setDurataCorso(corso.getDurataCorso());
        Docente docente = corso.getDocente();
        corsoDTONoDocente.setCognomeDocente(docente.getCognome());

        return corsoDTONoDocente;
    };

    public static CorsoDTO entityToDTO(Corso corso)
    {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setId(corso.getId());
        corsoDTO.setDataCorso(corso.getDataCorso());
        corsoDTO.setDurataCorso(corso.getDurataCorso());
        Docente docente = corso.getDocente();
        if (corso.getListaDiscenti()!=null)
        {
            List<Discente> listaDiscenti=corso.getListaDiscenti();
            for(Discente discente : listaDiscenti)
            {
                corsoDTO.addListaDiscenti(discente);
            }
        }
        corsoDTO.setDocente(docente);
        return corsoDTO;
    }

    public static Corso DTOToEntity(CorsoDTO corsoDTO, DocenteRepository docenteRepository)
    {
        Corso corso = new Corso();
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setId(corsoDTO.getId());
        corso.setDataCorso(corsoDTO.getDataCorso());
        corso.setDurataCorso(corsoDTO.getDurataCorso());
        if (corsoDTO.getListaDiscenti()!=null)
        {
            List<Discente> listaDiscenti=corsoDTO.getListaDiscenti();
            corso.setListaDiscenti(listaDiscenti);
        }
        if (corsoDTO.getDocente() != null && corsoDTO.getDocente().getId() != null) {
            Optional<Docente> docenteOptional = docenteRepository.findById(corsoDTO.getDocente().getId());
            if (docenteOptional.isPresent())
            {
                corso.setDocente(docenteOptional.get());
            }
            else
            {
                throw new EntityNotFoundException("Docente not found");
            }
        }
        return corso;
    }

    public static Corso updateCorsoFromDTO(CorsoDTO corsoDTO, Corso corso, DocenteRepository docenteRepository)
    {
        if(corsoDTO.getNomeCorso()!=null)
        {
            corso.setNomeCorso(corsoDTO.getNomeCorso());
        }
        if (corsoDTO.getDataCorso()!=null)
        {
            corso.setDataCorso(corsoDTO.getDataCorso());
        }
        if (corsoDTO.getDurataCorso()!=null)
        {
            corso.setDurataCorso(corsoDTO.getDurataCorso());
        }
        if (corsoDTO.getDocente() != null && corsoDTO.getDocente().getId() != null) {
            Optional<Docente> docenteOptional = docenteRepository.findById(corsoDTO.getDocente().getId());
            if (docenteOptional.isPresent()) {
                corso.setDocente(docenteOptional.get());
            } else {
                throw new EntityNotFoundException("Docente not found");
            }
        }
        return corso;
    }
}
