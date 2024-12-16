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

    @GetMapping("/getDiscenteById/{idDiscente}")
    public DiscenteDTONoCorso getDiscenteById(@PathVariable("idDiscente") Integer id)
    {
        return discenteService.getDiscenteById(id);
    }

    @GetMapping("/getAllDiscenti")
    public List<DiscenteDTONoCorso> getAllDiscenti()
    {
        return discenteService.getAllDiscenti();
    }

    @DeleteMapping("/deleteDiscenteById/{id_discente}")
    public String deleteDiscenteById(@PathVariable("id_discente") Integer id)
    {
        discenteService.deleteDiscenteById(id);
        return id.toString() + " delete sucessfully";
    }

    @PostMapping ("/createDiscente")
    public DiscenteDTONoCorso insertDiscente(@RequestBody DiscenteDTO discenteDTO)
    {
        return discenteService.createDiscente(discenteDTO);
    }

    @PutMapping("/updateDiscenteById/{id_discente}")
    public DiscenteDTONoCorso updateDiscenteById(@PathVariable ("id_discente") Integer id, @RequestBody DiscenteDTO discenteDTO)
    {
        return discenteService.updateDiscenteById(id, discenteDTO);
    }

    @PostMapping ("insertCorsoToDiscente/{id_discente}/{id_corso}")
    public DiscenteDTONoCorso insertCorsoToDiscente(@PathVariable ("id_discente")Integer idCorso,@PathVariable ("id_corso")Integer idDiscente)
    {
        return discenteService.insertCorsoToDiscente(idDiscente, idCorso);
    }
}
