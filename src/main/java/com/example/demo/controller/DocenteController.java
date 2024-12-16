package com.example.demo.controller;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")

public class DocenteController {

    private final DocenteService docenteService;
    //COSTRUTTORE DI DOCENTE CONTROLLER
    public DocenteController(DocenteService docenteService)
    {
        this.docenteService = docenteService;
    }

    //READ
    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable ("idDocente") Integer id)
    {
        return docenteService.getDocenteById(id);
    }

    //FIND ALL
    @GetMapping("/findAll")
    public List<DocenteDTO> findAll()
    {
        return docenteService.getAllDocenti();
    }

    //CREATE
    @PostMapping ("/insertDocente")
    public DocenteDTO insertDocente(@RequestBody DocenteDTO docenteDTO)
    {
        return docenteService.insertDocente(docenteDTO);
    }

    //UPDATE
    @PutMapping("/putDocenteById/{id_docente}")
    public DocenteDTO updateDocente(@PathVariable ("id_docente") Integer id, @RequestBody DocenteDTO docenteDTO)
    {
        return docenteService.updateDocenteById(id,docenteDTO);
    }

    //DELETE
    @DeleteMapping("/deleteDocenteById/{id_docente}")
    public String deleteDocente(@PathVariable ("id_docente") Integer id)
    {
        docenteService.deleteDocenteById(id);
        return id.toString() + " deleted Successfully";
    }
}
