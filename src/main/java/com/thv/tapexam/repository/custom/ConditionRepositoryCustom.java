package com.thv.tapexam.repository.custom;

public interface ConditionRepositoryCustom {
    Boolean checkExistedOther(Integer id, String code, Integer divisionId);
}
