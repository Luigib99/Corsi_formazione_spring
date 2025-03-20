package com.example.demo.DTO;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CorsoDTO {
    private Integer id;
    private String nomeCorso;
    private Date dataCorso;
    private String durataCorso;
    private DocenteDTO docente;
    private List<DiscenteDTO> listaDiscenti = new ArrayList<>();

    public void addListaDiscenti (DiscenteDTO discenteDTO) {this.listaDiscenti.add(discenteDTO);}
    public void removeDiscenti (DiscenteDTO discenteDTO) {this.listaDiscenti.remove(discenteDTO);}
}
