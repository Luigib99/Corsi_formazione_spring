package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.service.DiscenteService;
import org.springframework.web.bind.annotation.*;

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
    public DiscenteDTOFormat getDiscente(@PathVariable("idDiscente") Integer id)
    {
        return discenteService.getDiscente(id);
    }

    //READ ALL
    @GetMapping("/getAllDiscenti")
    public List<DiscenteDTOFormat> getAllDiscenti()
    {
        return discenteService.getAllDiscenti();
    }

    //CREATE
    @PostMapping ("/createDiscente/{id_corso}")
    public DiscenteDTOFormat createDiscente(@RequestBody DiscenteDTO discenteDTO, @PathVariable("id_corso") Integer idCorso)
    {
        return discenteService.createDiscente(discenteDTO, idCorso);
    }

    //UPDATE
    @PutMapping("/updateDiscente/{id_discente}")
    public DiscenteDTOFormat updateDiscente(@PathVariable ("id_discente") Integer id, @RequestBody DiscenteDTO discenteDTO)
    {
        return discenteService.updateDiscente(id, discenteDTO);
    }

    //DELETE
    @DeleteMapping("/deleteDiscente/{id_discente}")
    public String deleteDiscente(@PathVariable("id_discente") Integer id)
    {
        discenteService.deleteDiscente(id);
        return id.toString() + " delete sucessfully";
    }

    //INSERT CORSO TO DISCENTE
    @PostMapping ("insertCorsoToDiscente/{id_discente}/{id_corso}")
    public DiscenteDTOFormat insertCorsoToDiscente(@PathVariable ("id_discente")Integer idCorso, @PathVariable ("id_corso")Integer idDiscente)
    {
        return discenteService.insertCorsoToDiscente(idDiscente, idCorso);
    }

    //REMOVE CORSO TO DISCENTE
    @PutMapping ("removeCorsoToDiscente/{id_discente}/{id_corso}")
    public DiscenteDTOFormat removeCorsoToDiscente(@PathVariable ("id_discente")Integer idCorso, @PathVariable ("id_corso")Integer idDiscente)
    {
        return discenteService.removeCorsoToDiscente(idDiscente, idCorso);
    }
}
