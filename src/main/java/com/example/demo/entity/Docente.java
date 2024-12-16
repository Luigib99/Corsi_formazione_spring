package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "docente_test")

public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name="nome")
    private String nome;
    @Column (name="cognome")
    private String cognome;
    @OneToMany (mappedBy = "docente")
    private List<Corso> listaCorsi;

    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getId()
    {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCognome() {
        return cognome;
    }

    public void removeCorso (Corso corso)
    {
        listaCorsi.remove(corso);
    }
    public void addCorso(Corso corso)
    {
        listaCorsi.add(corso);
    }
    public List<Corso> getListaCorsi()
    {
        return listaCorsi;
    }
    public void setListaCorsi(List<Corso> listaCorsi)
    {
        this.listaCorsi = listaCorsi;
    }


}

