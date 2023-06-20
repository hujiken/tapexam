package com.thv.tapexam.repository.impl;

import com.thv.tapexam.repository.custom.DivisionRepositoryCustom;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivisionRepositoryImpl implements DivisionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean checkExistedOther(Integer id, String name, String code) {
        String sql = "SELECT id FROM division WHERE id <> :id AND (name = :name OR code = :code)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("code", code);
        List listId = query.getResultList();
        return listId.size() > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
