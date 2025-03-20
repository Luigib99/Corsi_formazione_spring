package com.example.demo.repository;

import com.example.demo.entity.Discente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DiscenteCustomRepositoryImpl implements DiscenteCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Discente> findFilteredDiscenti(Integer id, String nome, String cognome, Date dataNascita, String matricola) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Discente> query = cb.createQuery(Discente.class);
        Root<Discente> root = query.from(Discente.class);
        List<Predicate> predicates = new ArrayList<>();

        if (id != null && id!=0) {
            predicates.add(cb.equal(root.get("id"), id));
        }
        if (nome != null && !nome.isEmpty()) {
            predicates.add(cb.equal(root.get("nome"), nome));
        }
        if (cognome != null && !cognome.isEmpty()) {
            predicates.add(cb.equal(root.get("cognome"), cognome));
        }
        if(dataNascita != null) {
            predicates.add(cb.equal(root.get("dataNascita"), dataNascita));
        }
        if(matricola != null && !matricola.isEmpty()) {
            predicates.add(cb.equal(root.get("matricola"), matricola));
        }
        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
