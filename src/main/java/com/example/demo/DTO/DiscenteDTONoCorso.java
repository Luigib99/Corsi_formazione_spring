package com.example.demo.DTO;
import com.example.demo.entity.Corso;

import java.util.ArrayList;
import java.util.List;

public class DiscenteDTONoCorso {
    private int id;
    private String nome;
    private String cognome;
    private String matricola;
    private String dataNascita;
    private List <String> listaNomeCorsi = new ArrayList<>();

    public void setId(int id) {this.id = id;}
    public int getId() {
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
    public String getCognome() {return cognome;}

    public void setMatricola (String matricola) {this.matricola = matricola;}
    public String getMatricola() {return matricola;}

    public void setDataNascita(String dataNascita) {this.dataNascita = dataNascita;}
    public String getDataNascita() {return dataNascita;}

    public List<String> getNomeCorso() {return listaNomeCorsi;}
    public void addNomeCorso(String nomeCorso)
    {
        listaNomeCorsi.add(nomeCorso);
    }
    public void setListaNomeCorsi (List<String>listaNomeCorsi) {this.listaNomeCorsi = listaNomeCorsi;}

}
