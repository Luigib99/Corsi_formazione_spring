package com.example.demo.repository;
import com.example.demo.entity.Docente;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer>, DocenteCustomRepository {

    @Query(nativeQuery = true,value = "SELECT id,nome,cognome "+
                                        "FROM docente_test "  +
                                        "ORDER BY id ASC")
    List<Docente> query1();


    @Query(nativeQuery = true, value = "SELECT d.nome, d.cognome " +
            "FROM docente_test d " +
            "JOIN corso_test c ON d.id = c.id_docente " +
            "WHERE d.id = 1 AND d.nome = :nomeScelto AND d.cognome = :cognomeScelto AND c.nome_corso = :nomeCorsoScelto")
    List<Docente> FilteredDocente(@Param("nomeScelto") String nomeScelto,
                                  @Param("cognomeScelto") String cognomeScelto,
                                  @Param("nomeCorsoScelto") String nomeCorsoScelto);
}


