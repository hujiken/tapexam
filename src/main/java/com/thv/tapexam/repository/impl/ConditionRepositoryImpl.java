package com.thv.tapexam.repository.impl;

import com.thv.tapexam.repository.custom.ConditionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ConditionRepositoryImpl implements ConditionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean checkExistedOther(Integer id, String code, Integer divisionId) {
        String sql = "SELECT id FROM conditions WHERE id <> :id AND (code = :code OR division_id = :divisionId)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.setParameter("code", code);
        query.setParameter("divisionId", divisionId);
        List listId = query.getResultList();
        return listId.size() > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
