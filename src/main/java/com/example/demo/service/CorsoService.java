package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTOFormat;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final DiscenteRepository discenteRepository;

    public CorsoService(CorsoRepository corsoRepository, DocenteRepository docenteRepository, DiscenteRepository discenteRepository)
    {
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
        this.discenteRepository = discenteRepository;
    }

    //READ
    public CorsoDTOFormat getCorso(Integer id)
    {
        Optional<Corso>corso = corsoRepository.findById(id);
        if (corso.isPresent())
        {
            CorsoDTOFormat corsoDTOFormat = CorsoConverter.CorsoFormat(corso.get());
            return corsoDTOFormat;
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //READALL
    public List<CorsoDTOFormat> getAllCorsi()
    {
        List<Corso> listaCorsi = corsoRepository.findAll();
        List<CorsoDTOFormat> listaCorsiDTOFormat = new ArrayList<>();
        for (Corso corso : listaCorsi)
        {
            listaCorsiDTOFormat.add(CorsoConverter.CorsoFormat(corso));
        }
        return listaCorsiDTOFormat;
    }

    //UPDATE
    public CorsoDTOFormat updateCorso(Integer id, CorsoDTO corsoDTO)
    {
        Optional<Corso>corso = corsoRepository.findById(id);
        if (corso.isPresent())
        {
            corsoDTO.setId(id);
            Corso corsoModificato = CorsoConverter.DTOToEntity(corsoDTO);
            corsoModificato.setDocente(corso.get().getDocente());
            corsoModificato.setListaDiscenti(corso.get().getListaDiscenti());
            corsoRepository.save(corsoModificato);
            return CorsoConverter.CorsoFormat(corsoModificato);
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //UPDATE DOCENTE TO CORSO
    public CorsoDTOFormat updateDocenteToCorso(Integer idCorso, Integer idDocente)
    {
        Optional<Corso>corso = corsoRepository.findById(idCorso);
        Optional<Docente>docente = docenteRepository.findById(idDocente);
        if (corso.isPresent() && docente.isPresent())
        {
            if(corso.get().getDocente().getId()!=docente.get().getId())
            {
                corso.get().setDocente(docente.get());
                corsoRepository.save(corso.get());
            }
            else
            {
                throw new EntityNotFoundException("il docente tiene già il corso selezionato");
            }
        }
        else
        {
            throw new EntityNotFoundException("il docente o il corso non esiste");
        }
        return CorsoConverter.CorsoFormat(corso.get());
    }

    //DELETE
    public void deleteCorso(Integer id)
    {
        if (corsoRepository.existsById(id))
        {
            Corso corso = corsoRepository.findById(id).get();
            List<Docente>listaDocenti = docenteRepository.findAll();
            for (Docente docente : listaDocenti)
            {
                if(docente.getListaCorsi().contains(corso))
                {
                    if (docente.getListaCorsi().size()>1)
                    {
                        docente.removeCorso(corso);
                    }
                    else
                    {
                        throw new EntityNotFoundException("il docente " + docente.getId() + " " + docente.getCognome() + " deve per forza avere un corso");
                    }
                }
            }
            List<Discente>listaDiscenti = discenteRepository.findAll();
            for (Discente discente : listaDiscenti)
            {
                if(discente.getListaCorso().contains(corso))
                {
                    if (discente.getListaCorso().size()>1)
                    {
                        discente.removeCorso(corso);
                    }
                    else
                    {
                        throw new EntityNotFoundException("il discente " + discente.getId() + " " + discente.getCognome() + " deve per forza avere un corso");
                    }
                }
            }
            corsoRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //CREATE
    public CorsoDTOFormat createCorso(CorsoDTO corsoDTO, Integer idDocente)
    {
        Corso corso = CorsoConverter.DTOToEntity(corsoDTO);
        Docente docente = docenteRepository.findById(idDocente).get();
        corso.setDocente(docente);
        docente.addCorso(corso);
        corsoRepository.save(corso);
        return CorsoConverter.CorsoFormat(corso);
    }
}
