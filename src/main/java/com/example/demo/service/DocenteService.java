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
    public DocenteDTOFormat getDocente(Integer id)
    {
        Optional <Docente> docente= docenteRepository.findById(id);
        if(docente.isPresent())
        {
            DocenteDTOFormat docenteDTOFormat = DocenteConverter.entityToDTOFormat(docente.get());
            return docenteDTOFormat;
        }
        else
        {
            throw new EntityNotFoundException("Docente Not Found");
        }
    }

    //READ ALL
    public List<DocenteDTOFormat>getAllDocenti()
    {
        List<Docente> listaDocenti = docenteRepository.findAll();
        List<DocenteDTOFormat> listaDocentiDTOFormat=new ArrayList<DocenteDTOFormat>();

        for (Docente docente : listaDocenti)
        {
            DocenteDTOFormat docenteDTOFormat = DocenteConverter.entityToDTOFormat(docente);
            listaDocentiDTOFormat.add(docenteDTOFormat);
        }
        return listaDocentiDTOFormat;
    }

    //CREATE
    public DocenteDTO createDocente(DocenteDTO docenteDTO)
    {
        Docente docente = DocenteConverter.DTOToEntity(docenteDTO);
        docenteRepository.save(docente);
        return DocenteConverter.entityToDTO(docente);
    }

    //UPDATE
    public DocenteDTOFormat updateDocente(Integer id, DocenteDTO docenteDTO)
    {
        Optional <Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()) {
            docenteDTO.setId(id);
            Docente docenteModificato = DocenteConverter.DTOToEntity(docenteDTO);
            docenteModificato.setListaCorsi(docente.get().getListaCorsi());
            docenteRepository.save(docenteModificato);
            return DocenteConverter.entityToDTOFormat(docenteModificato);
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
    public List<DocenteDTOFormat> findFilteredDocenti(String nome, String cognome) {
        List<Docente> listaDocenti = docenteRepository.findFilteredDocenti(nome, cognome);
        List<DocenteDTOFormat> listaDocentiDTOFormat = new ArrayList<>();
        for (Docente docente : listaDocenti) {
            DocenteDTOFormat docenteDTOFormat = DocenteConverter.entityToDTOFormat(docente);
            listaDocentiDTOFormat.add(docenteDTOFormat);
        }
        return listaDocentiDTOFormat;
    }
}
