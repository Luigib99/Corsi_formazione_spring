package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTONoDocente;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;

    public CorsoService(CorsoRepository corsoRepository, DocenteRepository docenteRepository)
    {
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
    }

    //READ
    public CorsoDTONoDocente getCorsoById(Integer id)
    {
        Optional<Corso>corso = corsoRepository.findById(id);
        if (corso.isPresent())
        {
            CorsoDTONoDocente corsoDTONoDocente = CorsoConverter.CorsoIgnore(corso.get());
            return corsoDTONoDocente;
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //READALL
    public List<CorsoDTONoDocente> getAllCorso()
    {
        List<Corso> listaCorsi = corsoRepository.findAll();
        List<CorsoDTONoDocente> listaCorsiDTONoDocente = new ArrayList<>();
        for (Corso corso : listaCorsi)
        {
            listaCorsiDTONoDocente.add(CorsoConverter.CorsoIgnore(corso));
        }
        return listaCorsiDTONoDocente;
    }

    //UPDATE
    public CorsoDTONoDocente updateCorsoById(Integer id, CorsoDTO corsoDTO)
    {
        Optional<Corso>corso = corsoRepository.findById(id);
        if (corso.isPresent())
        {
            Corso nuovoCorso=corso.get();
            corsoDTO.setId(id);
            Corso corsoModificato = CorsoConverter.updateCorsoFromDTO(corsoDTO,nuovoCorso,docenteRepository);
            corsoRepository.save(corsoModificato);
            return CorsoConverter.CorsoIgnore(corsoModificato);
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //DELETE
    public void deleteCorsoById(Integer id)
    {
        if (corsoRepository.existsById(id))
        {
            corsoRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //CREATE
    public CorsoDTONoDocente insertCorso(CorsoDTO corsoDTO)
    {
        Corso corso = CorsoConverter.DTOToEntity(corsoDTO, docenteRepository);
        corsoRepository.save(corso);
        return CorsoConverter.CorsoIgnore(corso);
    }
}
