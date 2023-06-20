package com.thv.tapexam.repository.custom;

import com.thv.tapexam.entity.Division;

import java.util.List;

public interface DivisionRepositoryCustom {
    Boolean checkExistedOther(Integer id, String name, String code);
}
