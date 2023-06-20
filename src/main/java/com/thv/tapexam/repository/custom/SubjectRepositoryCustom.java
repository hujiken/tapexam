package com.thv.tapexam.repository.custom;

public interface SubjectRepositoryCustom {
    Boolean checkExistedOther(Integer id, String name, String code);
}
