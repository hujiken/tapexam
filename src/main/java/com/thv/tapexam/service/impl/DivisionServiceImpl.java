package com.thv.tapexam.service.impl;

import com.thv.tapexam.dto.request.DivisionRequest;
import com.thv.tapexam.entity.Division;
import com.thv.tapexam.exception.ErrorCode;
import com.thv.tapexam.exception.TapException;
import com.thv.tapexam.repository.base.DivisionRepository;
import com.thv.tapexam.service.base.DivisionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public Optional<Division> findById(Integer divisionId) throws Exception {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);
        if (!divisionOptional.isPresent()) {
            throw new TapException(ErrorCode.NOT_FOUND);
        }
        return divisionOptional;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addDivision(DivisionRequest divisionRequest) throws Exception {
        Optional<Division> divisionOptional = divisionRepository.findByNameOrCode(divisionRequest.getName(), divisionRequest.getCode());
        if (!divisionOptional.isEmpty()) {
            throw new TapException(ErrorCode.DIVISION_EXISTED);
        }
        if (StringUtils.isEmpty(divisionRequest.getName()) || StringUtils.isEmpty(divisionRequest.getCode())) {
            throw new TapException(ErrorCode.BAD_REQUEST);
        }
        Division division = Division.builder()
                .name(divisionRequest.getName())
                .code(divisionRequest.getCode())
                .enable(divisionRequest.getEnable())
                .build();
        divisionRepository.save(division);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDivision(Integer divisionId, DivisionRequest divisionRequest) throws Exception {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);
        if (!divisionOptional.isPresent()) {
            throw new TapException(ErrorCode.DIVISION_NOT_FOUND);
        }
        if (StringUtils.isEmpty(divisionRequest.getName()) || StringUtils.isEmpty(divisionRequest.getCode())) {
            throw new TapException(ErrorCode.BAD_REQUEST);
        }
        if (checkExistedOther(divisionId, divisionRequest.getName(), divisionRequest.getCode())) {
            throw new TapException(ErrorCode.DIVISION_NAME_EXISTED_OTHER);
        }
        Division division = Division.builder()
                .id(divisionId)
                .name(divisionRequest.getName())
                .code(divisionRequest.getCode())
                .enable(divisionRequest.getEnable())
                .build();
        divisionRepository.save(division);
        return Boolean.TRUE;
    }

    Boolean checkExistedOther(Integer id, String name, String code) {
        return divisionRepository.checkExistedOther(id, name, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDivision(Integer divisionId) throws Exception {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);
        if (!divisionOptional.isPresent()) {
            throw new TapException(ErrorCode.DIVISION_NOT_FOUND);
        }
        divisionRepository.deleteById(divisionId);
        return Boolean.TRUE;
    }

    @Override
    public Optional<List<Division>> getAllDivision() {
        return Optional.of(divisionRepository.findAll());
    }
}
