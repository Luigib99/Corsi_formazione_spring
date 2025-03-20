package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.service.DiscenteService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping ("/discente")
public class DiscenteController {
    private final DiscenteService discenteService;
    public DiscenteController(DiscenteService discenteService)
    {
        this.discenteService = discenteService;
    }

    //READ
    @GetMapping("/getDiscente/{idDiscente}")
    public DiscenteDTO getDiscente(@PathVariable("idDiscente") Integer id)
    {
        return discenteService.getDiscente(id);
    }

    //READ ALL
    @GetMapping("/getAllDiscenti")
    public List<DiscenteDTO> getAllDiscenti()
    {
        return discenteService.getAllDiscenti();
    }

    //CREATE
    @PostMapping ("/createDiscente/{id_corso}")
    public DiscenteDTO createDiscente(@RequestBody DiscenteDTO discenteDTO, @PathVariable("id_corso") Integer idCorso)
    {
        return discenteService.createDiscente(discenteDTO, idCorso);

    }
    //UPDATE
    @PutMapping("/updateDiscente/{id_discente}")
    public DiscenteDTO updateDiscente(@PathVariable ("id_discente") Integer id, @RequestBody DiscenteDTO discenteDTO)
    {
        return discenteService.updateDiscente(id, discenteDTO);
    }

    //DELETE
    @DeleteMapping("/deleteDiscente/{id_discente}")
    public void deleteDiscente(@PathVariable("id_discente") Integer id)
    {
        discenteService.deleteDiscente(id);
    }

    // GET DISCENTE BY ID CORSO

    @GetMapping ("getDiscenteByIdCorso/{id_corso}")
    public List<DiscenteDTO> getDiscenteByIdCorso(@PathVariable("id_corso") Integer idCorso){
        return discenteService.getDiscenteByIdCorso(idCorso);
    }

    //FILTERED DOCENTE

    @GetMapping("/filterDiscente")
    public List<DiscenteDTO> getFilteredDiscenti(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome,
            @RequestParam(required = false) Date dataNascita,
            @RequestParam(required = false) String matricola){
        return discenteService.findFilteredDiscenti(id, nome, cognome, dataNascita, matricola);
    }

    /*//INSERT CORSO TO DISCENTE
    @PostMapping ("insertCorsoToDiscente/{id_discente}/{id_corso}")
    public DiscenteDTO insertCorsoToDiscente(@PathVariable ("id_discente")Integer idCorso, @PathVariable ("id_corso")Integer idDiscente)
    {
        return discenteService.insertCorsoToDiscente(idDiscente, idCorso);
    }

    //REMOVE CORSO TO DISCENTE
    @PutMapping ("removeCorsoToDiscente/{id_discente}/{id_corso}")
    public DiscenteDTO removeCorsoToDiscente(@PathVariable ("id_discente")Integer idCorso, @PathVariable ("id_corso")Integer idDiscente)
    {
        return discenteService.removeCorsoToDiscente(idDiscente, idCorso);
    }*/
}
