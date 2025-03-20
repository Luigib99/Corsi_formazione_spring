package com.example.demo.repository;
import com.example.demo.entity.Docente;
import java.util.List;

public interface DocenteCustomRepository {
        List<Docente> findFilteredDocenti(Integer id, String nome, String cognome, String corso);
}
