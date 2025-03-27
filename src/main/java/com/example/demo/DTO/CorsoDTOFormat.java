package com.example.demo.DTO;

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
public class CorsoDTOFormat {
    private Integer id;
    private String nomeCorso;
    private Date dataCorso;
    private String durataCorso;
    private String cognomeDocente;
    private List<String> listaDiscenti = new ArrayList<>();


    public void addListaDiscenti (String cognomeDiscente) {this.listaDiscenti.add(cognomeDiscente);}
}
