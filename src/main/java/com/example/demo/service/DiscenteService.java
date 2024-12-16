package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTONoDocente;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DiscenteDTONoCorso;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscenteConverter;
import com.example.demo.utils.DocenteConverter;
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

    public DiscenteDTONoCorso getDiscenteById(Integer id)
    {
        Optional<Discente> discente= discenteRepository.findById(id);
        if(discente.isPresent())
        {
            DiscenteDTONoCorso discenteDTONoCorso = DiscenteConverter.DiscenteIgnore(discente.get());
            return discenteDTONoCorso;
        }
        else
        {
            throw new EntityNotFoundException("Discente Not Found");
        }
    }

    public List<DiscenteDTONoCorso> getAllDiscenti()
    {
        List<Discente> listaDiscenti= discenteRepository.findAll();
        List<DiscenteDTONoCorso> listaDiscentiDTONoCorso = new ArrayList<>();
        for (Discente discente : listaDiscenti)
        {
            DiscenteDTONoCorso discenteDTONoCorso = DiscenteConverter.DiscenteIgnore(discente);
            listaDiscentiDTONoCorso.add(discenteDTONoCorso);
        }
        return listaDiscentiDTONoCorso;
    }

    public void deleteDiscenteById(Integer id)
    {
        if (discenteRepository.existsById(id))
        {
            discenteRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Discente Not Found");
        }
    }

    public DiscenteDTONoCorso createDiscente(DiscenteDTO discenteDTO)

    {
        Discente discente = DiscenteConverter.DTOToEntity(discenteDTO,corsoRepository,false);
        discenteRepository.save(discente);
        return DiscenteConverter.DiscenteIgnore(discente);
    }

    public DiscenteDTONoCorso updateDiscenteById(Integer id, DiscenteDTO discenteDTO)
    {
        Optional<Discente>discente = discenteRepository.findById(id);
        if (discente.isPresent())
        {
            Discente nuovoDiscente=discente.get();
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

    public DiscenteDTONoCorso insertCorsoToDiscente (Integer idCorso, Integer idDiscente)
    {
        Optional<Discente>discente = discenteRepository.findById(idDiscente);
        Optional<Corso> corso = corsoRepository.findById(idCorso);
        if (discente.isPresent() && corso.isPresent())
        {
            DiscenteDTO discenteDTO = DiscenteConverter.entityToDTO(discente.get());
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso.get());
            Corso corsoSelezionato=CorsoConverter.DTOToEntity(corsoDTO,docenteRepository);
            discenteDTO.addCorso(corsoSelezionato);
            Discente discenteSalvato  = DiscenteConverter.DTOToEntity(discenteDTO,corsoRepository,true);
            corsoSelezionato.addListaDiscenti(discenteSalvato);
            //INSERISCO NEL DB
            corsoRepository.save(corsoSelezionato);
            discenteRepository.save(discenteSalvato);
            DiscenteDTONoCorso discenteDTONoCorso = DiscenteConverter.DiscenteIgnore(discenteSalvato);
            return discenteDTONoCorso;
        }
        else
        {
            throw new EntityNotFoundException("Discente or Corso Not Found");
        }
    }
}
