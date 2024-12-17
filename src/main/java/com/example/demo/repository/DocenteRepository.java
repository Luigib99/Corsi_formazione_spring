package com.example.demo.repository;
import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository <Docente, Integer> {
    List<Docente> findDistinctTopByNomeContaining(String nome);
    @Query(nativeQuery = true,value = "SELECT id,nome,cognome "+
                            "FROM docente_test "  +
                            "ORDER BY id ASC")
    List<Docente> query1();
}
