package com.thv.tapexam.service.base;

import com.thv.tapexam.dto.request.ConditionRequest;
import com.thv.tapexam.entity.Condition;

import java.util.List;
import java.util.Optional;

public interface ConditionService {
    Optional<Condition> findById(Integer conditionId) throws Exception;

    Optional<List<Condition>> getAllCondition();

    Boolean addCondition(ConditionRequest conditionRequest) throws Exception;

    Boolean updateCondition(Integer conditionId, ConditionRequest conditionRequest) throws Exception;

    Boolean deleteCondition(Integer conditionId) throws Exception;
}
