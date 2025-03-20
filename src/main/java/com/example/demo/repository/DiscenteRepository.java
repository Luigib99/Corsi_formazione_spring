package com.example.demo.repository;

import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Integer>, DiscenteCustomRepository{
    @Query(nativeQuery = true,value = "SELECT d.* "+
    "FROM discente_test d "+
    "JOIN discente_corso dc "+
    "ON dc.id_discente=d.id "+
    "JOIN corso_test c "+
    "ON c.id=dc.id_corso "+
    "WHERE c.id=:idCorsoScelto")
    List<Discente> getDiscenteByIdCorso(@Param("idCorsoScelto") Integer idCorsoScelto);
}
