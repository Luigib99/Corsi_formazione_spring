package com.example.demo.DTO;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;

import java.util.ArrayList;
import java.util.List;

public class CorsoDTO {
    private Integer id;
    private String nomeCorso;
    private String dataCorso;
    private String durataCorso;
    private Docente docente;
    private List<Discente> listaDiscenti = new ArrayList<>();

    public Integer getId ()
    {
        return id;
    }
    public void setId (Integer id)
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

    public Docente getDocente () {return docente;}
    public void setDocente (Docente docente)  {this.docente = docente;}

    public List<Discente> getListaDiscenti () {return listaDiscenti;}
    public void addListaDiscenti (Discente discente) {this.listaDiscenti.add(discente);}
}
