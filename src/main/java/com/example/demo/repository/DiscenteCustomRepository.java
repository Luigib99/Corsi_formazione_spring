package com.example.demo.repository;

import com.example.demo.entity.Discente;

import java.util.Date;
import java.util.List;

public interface DiscenteCustomRepository {
    List<Discente> findFilteredDiscenti(Integer id, String nome, String cognome, Date dataNascita, String matricola);
}
