package com.example.demo.repository;

import com.example.demo.entity.Docente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocenteCustomRepositoryImpl implements DocenteCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Docente> findFilteredDocenti(String nome, String cognome) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Docente> query = cb.createQuery(Docente.class);
        Root<Docente> root = query.from(Docente.class);
        List<Predicate> predicates = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            predicates.add(cb.equal(root.get("nome"), nome));
        }
        if (cognome != null && !cognome.isEmpty()) {
            predicates.add(cb.equal(root.get("cognome"), cognome));
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
