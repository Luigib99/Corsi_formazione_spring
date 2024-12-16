package com.example.demo.service;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
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
    public DocenteService(DocenteRepository docenteRepository)
    {
        this.docenteRepository = docenteRepository;
    }

    //READ
    public DocenteDTO getDocenteById(Integer id)
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
    public DocenteDTO insertDocente(DocenteDTO docenteDTO)
    {
        Docente docente = DocenteConverter.DTOToEntity(docenteDTO);
        docenteRepository.save(docente);
        return DocenteConverter.entityToDTO(docente);
    }
    //UPDATE
    public DocenteDTO updateDocenteById(Integer id, DocenteDTO docenteDTO)
    {
        Optional <Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            docenteDTO.setId(id);
            Docente docenteModificato = DocenteConverter.DTOToEntity(docenteDTO);
            docenteRepository.save(docenteModificato);
            return DocenteConverter.entityToDTO(docenteModificato);
        }
        else
        {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }

    public void deleteDocenteById(Integer id)
    {
        if (docenteRepository.existsById(id))
        {
            docenteRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }
}
