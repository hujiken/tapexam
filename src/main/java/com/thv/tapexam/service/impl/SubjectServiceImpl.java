package com.thv.tapexam.service.impl;

import com.thv.tapexam.dto.request.SubjectRequest;
import com.thv.tapexam.entity.Subject;
import com.thv.tapexam.exception.ErrorCode;
import com.thv.tapexam.exception.TapException;
import com.thv.tapexam.repository.base.SubjectRepository;
import com.thv.tapexam.service.base.SubjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> findById(Integer subjectId) throws Exception {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (!subjectOptional.isPresent()) {
            throw new TapException(ErrorCode.NOT_FOUND);
        }
        return subjectOptional;
    }

    @Override
    public Optional<List<Subject>> getAllSubject() {
        return Optional.of(subjectRepository.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addSubject(SubjectRequest subjectRequest) throws Exception {
        Optional<Subject> subjectOptional = subjectRepository.findByNameOrCode(subjectRequest.getName(), subjectRequest.getCode());
        if (!subjectOptional.isEmpty()) {
            throw new TapException(ErrorCode.SUBJECT_EXISTED);
        }
        if (StringUtils.isEmpty(subjectRequest.getName()) || StringUtils.isEmpty(subjectRequest.getCode())) {
            throw new TapException(ErrorCode.BAD_REQUEST);
        }
        Subject subject = Subject.builder()
                .name(subjectRequest.getName())
                .code(subjectRequest.getCode())
                .enable(subjectRequest.getEnable())
                .build();
        subjectRepository.save(subject);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateSubject(Integer subjectId, SubjectRequest subjectRequest) throws Exception {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (!subjectOptional.isPresent()) {
            throw new TapException(ErrorCode.SUBJECT_NOT_FOUND);
        }
        if (StringUtils.isEmpty(subjectRequest.getName()) || StringUtils.isEmpty(subjectRequest.getCode())) {
            throw new TapException(ErrorCode.BAD_REQUEST);
        }
        if (checkExistedOther(subjectId, subjectRequest.getName(), subjectRequest.getCode())) {
            throw new TapException(ErrorCode.SUBJECT_NAME_EXISTED_OTHER);
        }
        Subject subject = Subject.builder()
                .id(subjectId)
                .name(subjectRequest.getName())
                .code(subjectRequest.getCode())
                .enable(subjectRequest.getEnable())
                .build();
        subjectRepository.save(subject);
        return Boolean.TRUE;
    }

    Boolean checkExistedOther(Integer id, String name, String code) {
        return subjectRepository.checkExistedOther(id, name, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSubject(Integer subjectId) throws Exception {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (!subjectOptional.isPresent()) {
            throw new TapException(ErrorCode.SUBJECT_NOT_FOUND);
        }
        subjectRepository.deleteById(subjectId);
        return Boolean.TRUE;
    }
}
