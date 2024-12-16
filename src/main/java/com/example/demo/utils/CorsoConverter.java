package com.example.demo.utils;
import com.example.demo.DTO.*;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CorsoConverter {

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

    public static Corso DTOToEntity(CorsoDTO corsoDTO)
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
        if (corsoDTO.getDocente()!=null)
        {
            Docente docente=corsoDTO.getDocente();
            corso.setDocente(docente);
        }
        return corso;
    }

    public static CorsoDTOFormat CorsoFormat(Corso corso)
    {
        CorsoDTOFormat corsoDTOFormat = new CorsoDTOFormat();
        corsoDTOFormat.setNomeCorso(corso.getNomeCorso());
        corsoDTOFormat.setId(corso.getId());
        corsoDTOFormat.setDataCorso(corso.getDataCorso());
        corsoDTOFormat.setDurataCorso(corso.getDurataCorso());
        Docente docente = corso.getDocente();
        if (docente!=null)
        {
            corsoDTOFormat.setCognomeDocente(docente.getCognome());
        }
        List<String> listaDiscenti=new ArrayList<String>();
        if(corso.getListaDiscenti()!=null)
        {
            for (Discente discente : corso.getListaDiscenti())
            {
                String cognome=discente.getCognome();
                listaDiscenti.add(cognome);
            }
            corsoDTOFormat.setListaDiscenti(listaDiscenti);
        }
        return corsoDTOFormat;
    }
}
