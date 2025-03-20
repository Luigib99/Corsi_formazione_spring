package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "corso_test")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "nome_corso")
    private String nomeCorso;

    @Column (name = "data_corso")
    private Date dataCorso;

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

    public void addDiscenti (Discente discente) {this.listaDiscenti.add(discente);}
    public void removeDiscenti (Discente discente)
    {
        this.listaDiscenti.remove(discente);
    }
}
