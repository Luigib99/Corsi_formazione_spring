package com.example.demo.DTO;
import com.example.demo.entity.Discente;

import java.util.ArrayList;
import java.util.List;

public class CorsoDTONoDocente {
    private Integer id;
    private String nomeCorso;
    private String dataCorso;
    private String durataCorso;
    private String cognomeDocente;
    private List<Discente> listaDiscenti = new ArrayList<>();

    public int getId ()
    {
        return id;
    }
    public void setId (int id)
    {
        this.id = id;
    }

    public void setNomeCorso(String nomeCorso)
    {
        this.nomeCorso = nomeCorso;
    }
    public String getNomeCorso ()
    {
        return nomeCorso;
    }

    public void setDataCorso (String dataCorso)
    {
        this.dataCorso = dataCorso;
    }
    public String getDataCorso()
    {
        return dataCorso;
    }

    public void setDurataCorso (String durataCorso)
    {
        this.durataCorso = durataCorso;
    }
    public String getDurataCorso ()
    {
        return durataCorso;
    }

    public String getCognomeDocente () {return cognomeDocente;}
    public void setCognomeDocente (String cognomeDocente)  {this.cognomeDocente = cognomeDocente;}

    public List<Discente> getListaDiscenti () {return listaDiscenti;}
    public void addListaDiscenti (Discente discente) {this.listaDiscenti.add(discente);}
}