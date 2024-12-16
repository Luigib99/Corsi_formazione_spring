package com.example.demo.service;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DiscenteDTOFormat;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.DiscenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {
    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;

    public DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository, DocenteRepository docenteRepository)
    {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
        this.docenteRepository = docenteRepository;
    }

    //READ
    public DiscenteDTOFormat getDiscente(Integer id)
    {
        Optional<Discente> discente= discenteRepository.findById(id);
        if(discente.isPresent())
        {
            DiscenteDTOFormat discenteDTOFormat = DiscenteConverter.DiscenteIgnore(discente.get());
            return discenteDTOFormat;
        }
        else
        {
            throw new EntityNotFoundException("Discente Not Found");
        }
    }

    //READALL
    public List<DiscenteDTOFormat> getAllDiscenti()
    {
        List<Discente> listaDiscenti= discenteRepository.findAll();
        List<DiscenteDTOFormat> listaDiscentiDTONoCorso = new ArrayList<>();
        for (Discente discente : listaDiscenti)
        {
            DiscenteDTOFormat discenteDTOFormat = DiscenteConverter.DiscenteIgnore(discente);
            listaDiscentiDTONoCorso.add(discenteDTOFormat);
        }
        return listaDiscentiDTONoCorso;
    }

    //CREATE
    public DiscenteDTOFormat createDiscente(DiscenteDTO discenteDTO, Integer corsoId)
    {
        Corso corso = corsoRepository.findById(corsoId).get();
        Discente discente = DiscenteConverter.DTOToEntity(discenteDTO,corsoRepository,false);
        corso.addDiscenti(discente);
        discente.addCorso(corso);
        discenteRepository.save(discente);
        corsoRepository.save(corso);
        return DiscenteConverter.DiscenteIgnore(discente);
    }

    //UPDATE
    public DiscenteDTOFormat updateDiscente(Integer id, DiscenteDTO discenteDTO)
    {
        Optional<Discente>discente = discenteRepository.findById(id);
        if (discente.isPresent())
        {
            discenteDTO.setId(id);
            Discente discenteModificato = DiscenteConverter.DTOToEntity(discenteDTO,corsoRepository,true);
            discenteRepository.save(discenteModificato);
            return DiscenteConverter.DiscenteIgnore(discenteModificato);
        }
        else
        {
            throw new EntityNotFoundException("Discente Not Found");
        }
    }

    //DELETE
    public void deleteDiscente(Integer id)
    {
        if (discenteRepository.existsById(id))
        {
            Discente discente = discenteRepository.findById(id).get();
            for (Corso corso : corsoRepository.findAll())
            {
                List<Discente>listaDiscenti=corso.getListaDiscenti();
                if (listaDiscenti.contains(discente))
                {
                    corso.removeDiscenti(discente);
                }
            }
            discenteRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Discente Not Found");
        }
    }

    //INSERT CORSO TO DISCENTE
    public DiscenteDTOFormat insertCorsoToDiscente (Integer idCorso, Integer idDiscente)
    {
        //PRENDO CORSO E DISCENTE
        Discente discente = discenteRepository.findById(idDiscente).orElseThrow();
        Corso corso = corsoRepository.findById(idCorso).orElseThrow();
        //SE ESISTONO
        DiscenteDTO discenteDTO = DiscenteConverter.entityToDTO(discente);
        //INSERISCO NEL DB
        List<Corso>listaCorsi = discente.getListaCorso();
        if (!listaCorsi.contains(corso))
        {
            discenteDTO.addCorso(corso);
            corso.addDiscenti(discente);
            corsoRepository.save(corso);
            discenteRepository.save(discente);
        }
        else
        {
            throw new EntityNotFoundException("Corso già presente");
        }
        DiscenteDTOFormat discenteDTOFormat = DiscenteConverter.DiscenteIgnore(discente);
        return discenteDTOFormat;
    }

    //REMOVE CORSO TO DISCENTE
    public DiscenteDTOFormat removeCorsoToDiscente (Integer idCorso, Integer idDiscente)
    {
        //PRENDO CORSO E DISCENTE
        Discente discente = discenteRepository.findById(idDiscente).orElseThrow();
        Corso corso = corsoRepository.findById(idCorso).orElseThrow();
        //SE ESISTONO
        DiscenteDTO discenteDTO = DiscenteConverter.entityToDTO(discente);
        //INSERISCO NEL DB
        List<Corso>listaCorsi = discente.getListaCorso();
        if (listaCorsi.contains(corso))
        {
            if (listaCorsi.size()>1)
            {
                discenteDTO.removeCorso(corso);
                corso.removeDiscenti(discente);
                corsoRepository.save(corso);
                discenteRepository.save(discente);
            }
            else
            {
                throw new EntityNotFoundException("Il discente deve avere un corso");
            }
        }
        else
        {
            throw new EntityNotFoundException("Corso non presente nel discente");
        }
        DiscenteDTOFormat discenteDTOFormat = DiscenteConverter.DiscenteIgnore(discente);
        return discenteDTOFormat;
    }
}
