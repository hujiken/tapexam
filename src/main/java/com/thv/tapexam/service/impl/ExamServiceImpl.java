package com.thv.tapexam.service.impl;

import com.thv.tapexam.repository.base.ConditionRepository;
import com.thv.tapexam.repository.base.DivisionRepository;
import com.thv.tapexam.repository.base.SubjectRepository;
import com.thv.tapexam.service.base.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer getResultExam() throws Exception {
        return null;
    }
}
