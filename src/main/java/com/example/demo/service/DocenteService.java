package com.example.demo.service;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteDTOFormat;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final CorsoRepository corsoRepository;

    public DocenteService(DocenteRepository docenteRepository, CorsoRepository corsoRepository)
    {
        this.docenteRepository = docenteRepository;
        this.corsoRepository = corsoRepository;
    }

    //READ
    public DocenteDTO getDocente(Integer id)
    {
        Optional <Docente> docente= docenteRepository.findById(id);
        if(docente.isPresent())
        {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente.get());
            return docenteDTO;
        }
        else
        {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }

    //READ ALL
    public List<DocenteDTO>getAllDocenti()
    {
        List<Docente> listaDocenti = docenteRepository.findAll();
        List<DocenteDTO> listaDocentiDTO=new ArrayList<DocenteDTO>();

        for (Docente docente : listaDocenti)
        {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente);
            listaDocentiDTO.add(docenteDTO);
        }
        return listaDocentiDTO;
    }

    //CREATE
    public DocenteDTO createDocente(DocenteDTO docenteDTO)
    {
        Docente docente = DocenteConverter.DTOToEntity(docenteDTO);
        docenteRepository.save(docente);
        return DocenteConverter.entityToDTO(docente);
    }

    //UPDATE
    public DocenteDTO updateDocente(Integer id, DocenteDTO docenteDTO)
    {
        Optional <Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            docenteDTO.setId(id);
            Docente docenteModificato = DocenteConverter.DTOToEntity(docenteDTO);
            docenteModificato.setListaCorsi(docente.get().getListaCorsi());
            docenteRepository.save(docenteModificato);
            return DocenteConverter.entityToDTO(docenteModificato);
        }
        else
        {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }

    //DELETE
    public void deleteDocente(Integer id)
    {
        Docente docente = docenteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        docenteRepository.delete(docente);
    }

    //FILTER DOCENTE
    public List<DocenteDTO> findFilteredDocenti(Integer id, String nome, String cognome, String corso) {
        List<Docente> listaDocenti = docenteRepository.findFilteredDocenti(id, nome, cognome, corso);
        List<DocenteDTO> listaDocentiDTO = new ArrayList<>();
        for (Docente docente : listaDocenti) {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente);
            listaDocentiDTO.add(docenteDTO);
        }
        return listaDocentiDTO;
    }
}
