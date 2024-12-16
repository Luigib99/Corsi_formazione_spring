package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTOFormat;
import com.example.demo.service.CorsoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/corso")
public class CorsoController {
    private final CorsoService corsoService;

    public CorsoController(CorsoService corsoService)
    {
        this.corsoService = corsoService;
    }

    //READ
    @GetMapping("/getCorso/{id_corso}")
    public CorsoDTOFormat getCorso(@PathVariable("id_corso") Integer id)
    {
        return corsoService.getCorso(id);
    }

    //READ ALL
    @GetMapping("/getAllCorsi")
    public List<CorsoDTOFormat> getAllCorsi()
    {
        return corsoService.getAllCorsi();
    }

    //CREATE
    @PostMapping ("/createCorso/{id_docente}")
    public CorsoDTOFormat createCorso(@RequestBody CorsoDTO corsoDTO, @PathVariable("id_docente") Integer idDocente)
    {
        return corsoService.createCorso(corsoDTO,idDocente);
    }

    //UPDATE
    @PutMapping("/updateCorso/{id_corso}")
    public CorsoDTOFormat updateCorso(@PathVariable ("id_corso") Integer id, @RequestBody CorsoDTO corsoDTO)
    {
        return corsoService.updateCorso(id, corsoDTO);
    }

    //UPDATE DOCENTE TO CORSO
    @PutMapping("/updateDocenteToCorso/{id_corso}/{id_docente}")
    public CorsoDTOFormat updateDocenteToCorso(@PathVariable ("id_corso") Integer idCorso, @PathVariable ("id_docente") Integer idDocente)
    {
        return corsoService.updateDocenteToCorso(idCorso,idDocente);
    }

    //DELETE
    @DeleteMapping("/deleteCorso/{id_corso}")
    public String deleteCorso(@PathVariable("id_corso") Integer id)
    {
        corsoService.deleteCorso(id);
        return id.toString() + " delete sucessfully";
    }


}
