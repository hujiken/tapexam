package com.thv.tapexam.service.base;

import com.thv.tapexam.dto.request.DivisionRequest;
import com.thv.tapexam.entity.Division;

import java.util.List;
import java.util.Optional;

public interface DivisionService {
    Optional<Division> findById(Integer divisionId) throws Exception;

    Boolean addDivision(DivisionRequest divisionRequest) throws Exception;

    Boolean updateDivision(Integer divisionId, DivisionRequest divisionRequest) throws Exception;

    Boolean deleteDivision(Integer divisionId) throws Exception;

    Optional<List<Division>> getAllDivision();
}
