package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.CorsoDTONoDocente;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
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
    @GetMapping("/getCorsoById/{id_corso}")
    public CorsoDTONoDocente getCorsoById(@PathVariable("id_corso") Integer id)
    {
        return corsoService.getCorsoById(id);
    }

    //READ ALL
    @GetMapping("/getAllCorso")
    public List<CorsoDTONoDocente> getAllCorso()
    {
        return corsoService.getAllCorso();
    }

    //UPDATE
    @PutMapping("/updateCorsoById/{id_corso}")
    public CorsoDTONoDocente updateCorsoById(@PathVariable ("id_corso") Integer id, @RequestBody CorsoDTO corsoDTO)
    {
        return corsoService.updateCorsoById(id, corsoDTO);
    }

    //DELETE
    @DeleteMapping("/deleteCorsoById/{id_corso}")
    public String deleteCorsoById(@PathVariable("id_corso") Integer id)
    {
        corsoService.deleteCorsoById(id);
        return id.toString() + " delete sucessfully";
    }

    //INSERT
    @PostMapping ("/insertCorso")
    public CorsoDTONoDocente insertCorso(@RequestBody CorsoDTO corsoDTO)
    {
        return corsoService.insertCorso(corsoDTO);
    }
}
