package com.example.demo.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corso_test")

public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "nome_corso")
    private String nomeCorso;
    @Column (name = "data_corso")
    private String dataCorso;
    @Column (name = "durata_corso")
    private String durataCorso;
    @ManyToOne
    @JoinColumn(name = "id_docente")
    private Docente docente;
    @ManyToMany
    @JoinTable(name = "discente_corso" ,
            joinColumns = @JoinColumn (name = "id_corso"),
            inverseJoinColumns = @JoinColumn (name = "id_discente"))
    private List<Discente> listaDiscenti;

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
    public void setListaDiscenti (List<Discente> listaDiscenti)
    {
        this.listaDiscenti = listaDiscenti;
    }
    public void addDiscenti (Discente discente) {this.listaDiscenti.add(discente);}
    public void removeDiscenti (Discente discente)
    {
        this.listaDiscenti.remove(discente);
    }
}
