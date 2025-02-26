package com.example.demo.controller;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteDTOFormat;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")

public class DocenteController {

    private final DocenteService docenteService;
    public DocenteController(DocenteService docenteService)
    {
        this.docenteService = docenteService;
    }

    //READ
    @GetMapping("/getDocente/{idDocente}")
    public DocenteDTOFormat getDocente(@PathVariable ("idDocente") Integer id)
    {
        return docenteService.getDocente(id);
    }

    //FIND ALL
    @GetMapping("/getAllDocenti")
    public List<DocenteDTOFormat> getALLDocenti()
    {
        return docenteService.getAllDocenti();
    }

    //CREATE
    @PostMapping ("/createDocente")
    public DocenteDTO createDocente(@RequestBody DocenteDTO docenteDTO)
    {
        return docenteService.createDocente(docenteDTO);
    }

    //UPDATE
    @PutMapping("/updateDocente/{id_docente}")
    public DocenteDTOFormat updateDocente(@PathVariable ("id_docente") Integer id, @RequestBody DocenteDTO docenteDTO)
    {
        return docenteService.updateDocente(id,docenteDTO);
    }

    //DELETE
    @DeleteMapping("/deleteDocente/{id_docente}")
    public void  deleteDocente(@PathVariable ("id_docente") Integer id)
    {
        docenteService.deleteDocente(id);
    }

    //FILTERED DOCENTE

    @GetMapping("/filterDocente")
    public List<DocenteDTOFormat> getFilteredDocenti(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome) {
        return docenteService.findFilteredDocenti(nome, cognome);
    }
}
