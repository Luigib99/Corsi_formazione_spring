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
    public CorsoDTO getCorso(Integer id)
    {
        Optional<Corso>corso = corsoRepository.findById(id);
        if (corso.isPresent())
        {
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso.get());
            return corsoDTO;
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
    }

    //READALL
    public List<CorsoDTO> getAllCorsi()
    {
        List<Corso> listaCorsi = corsoRepository.findAll();
        List<CorsoDTO> listaCorsiDTO = new ArrayList<>();
        for (Corso corso : listaCorsi)
        {
            listaCorsiDTO.add(CorsoConverter.entityToDTO(corso));
        }
        return listaCorsiDTO;
    }

    //UPDATE
    public CorsoDTO updateCorso(Integer id, CorsoDTO corsoDTO)
    {
        Optional<Corso>corso = corsoRepository.findById(id);
        if (corso.isPresent())
        {
            corsoDTO.setId(id);
            Corso corsoModificato = CorsoConverter.DTOToEntity(corsoDTO);
            corsoModificato.setDocente(corso.get().getDocente());
            corsoModificato.setListaDiscenti(corso.get().getListaDiscenti());
            corsoRepository.save(corsoModificato);
            return CorsoConverter.entityToDTO(corsoModificato);
        }
        else
        {
            throw new EntityNotFoundException("Corso Not Found");
        }
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
                if(discente.getListaCorsi().contains(corso))
                {
                    if (discente.getListaCorsi().size()>1)
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
    public CorsoDTO createCorso(CorsoDTO corsoDTO, Integer idDocente)
    {
        Corso corso = CorsoConverter.DTOToEntity(corsoDTO);
        Docente docente = docenteRepository.findById(idDocente).get();
        corso.setDocente(docente);
        docente.addCorso(corso);
        corsoRepository.save(corso);
        return CorsoConverter.entityToDTO(corso);
    }

    //UPDATE DOCENTE TO CORSO
    /*public CorsoDTO updateDocenteToCorso(Integer idCorso, Integer idDocente)
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
        return CorsoConverter.entityToDTO(corso.get());
    }*/
}
