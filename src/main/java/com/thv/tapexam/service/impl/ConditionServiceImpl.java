package com.thv.tapexam.service.impl;

import com.thv.tapexam.dto.request.ConditionRequest;
import com.thv.tapexam.entity.Condition;
import com.thv.tapexam.exception.ErrorCode;
import com.thv.tapexam.exception.TapException;
import com.thv.tapexam.repository.base.ConditionRepository;
import com.thv.tapexam.service.base.ConditionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ConditionRepository conditionRepository;

    @Override
    public Optional<Condition> findById(Integer conditionId) throws Exception {
        Optional<Condition> conditionOptional = conditionRepository.findById(conditionId);
        if (!conditionOptional.isPresent()) {
            throw new TapException(ErrorCode.NOT_FOUND);
        }
        return conditionOptional;
    }

    @Override
    public Optional<List<Condition>> getAllCondition() {
        return Optional.of(conditionRepository.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCondition(ConditionRequest conditionRequest) throws Exception {
        Optional<Condition> conditionOptional = conditionRepository.findByCode(conditionRequest.getCode());
        if (!conditionOptional.isEmpty()) {
            throw new TapException(ErrorCode.CONDITION_NOT_FOUND);
        }
        if (StringUtils.isEmpty(conditionRequest.getCode()) || conditionRequest.getDivisionId() == null) {
            throw new TapException(ErrorCode.BAD_REQUEST);
        }
        Condition condition = Condition.builder()
                .code(conditionRequest.getCode())
                .divisionId(conditionRequest.getDivisionId())
                .divisionPoint(conditionRequest.getDivisionPoint())
                .totalPoint(conditionRequest.getTotalPoint())
                .enable(conditionRequest.getEnable())
                .build();
        conditionRepository.save(condition);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCondition(Integer conditionId, ConditionRequest conditionRequest) throws Exception {
        Optional<Condition> conditionOptional = conditionRepository.findById(conditionId);
        if (!conditionOptional.isPresent()) {
            throw new TapException(ErrorCode.CONDITION_NOT_FOUND);
        }
        if (StringUtils.isEmpty(conditionRequest.getCode()) || conditionRequest.getDivisionId() == null) {
            throw new TapException(ErrorCode.BAD_REQUEST);
        }
        if (checkExistedOther(conditionId, conditionRequest.getCode(), conditionRequest.getDivisionId())) {
            throw new TapException(ErrorCode.CONDITION_CODE_EXISTED_OTHER);
        }
        Condition condition = Condition.builder()
                .id(conditionId)
                .code(conditionRequest.getCode())
                .divisionId(conditionRequest.getDivisionId())
                .divisionPoint(conditionRequest.getDivisionPoint())
                .totalPoint(conditionRequest.getTotalPoint())
                .enable(conditionRequest.getEnable())
                .build();
        conditionRepository.save(condition);
        return Boolean.TRUE;
    }

    Boolean checkExistedOther(Integer id, String code, Integer divisionId) {
        return conditionRepository.checkExistedOther(id, code, divisionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCondition(Integer conditionId) throws Exception {
        Optional<Condition> conditionOptional = conditionRepository.findById(conditionId);
        if (!conditionOptional.isPresent()) {
            throw new TapException(ErrorCode.CONDITION_NOT_FOUND);
        }
        conditionRepository.deleteById(conditionId);
        return Boolean.TRUE;
    }
}
