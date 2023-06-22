package com.thv.tapexam.repository.custom;

public interface ConditionRepositoryCustom {
    Boolean checkExistedOther(Integer id, String name, String code, String divisionCode);
}
