package com.thv.tapexam.service.impl;

import com.thv.tapexam.entity.Condition;
import com.thv.tapexam.entity.Subject;
import com.thv.tapexam.exception.ErrorCode;
import com.thv.tapexam.exception.TapException;
import com.thv.tapexam.repository.base.ConditionRepository;
import com.thv.tapexam.repository.base.SubjectRepository;
import com.thv.tapexam.service.base.ExamService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Value("${input.file}")
    private String inputFile;

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer getResultExam() throws Exception {
        List<Subject> subjectList = subjectRepository.findAllByEnable(Boolean.TRUE);
        List<Condition> conditionList = conditionRepository.findAllByEnable(Boolean.TRUE);

        if (subjectList.isEmpty()) {
            throw new TapException(ErrorCode.SUBJECT_NOT_DATA);
        }
        if (conditionList.isEmpty()) {
            throw new TapException(ErrorCode.CONDITION_NOT_DATA);
        }

        Path path = Paths.get(inputFile);
        BufferedReader reader = Files.newBufferedReader(path);
        String numberLine = reader.readLine();
        if (numberLine == null) {
            throw new TapException(ErrorCode.FILE_EMPTY);
        }
        Integer numberExam = Integer.parseInt(numberLine);

        String subjectLine = reader.readLine();
        String[] codeSubjectArr = subjectLine.split(" ");
        Map<String, Integer> subjectOrdinalMap = new HashMap<>();
        for (int i = 0; i < codeSubjectArr.length; i++) {
            subjectOrdinalMap.put(codeSubjectArr[i], i + 1);
        }

        Integer count = 0;
        String line;
        Integer result = 0;

        while (count < numberExam && !Strings.isEmpty(line = reader.readLine())) {
            String[] strArr = line.split(" ");
            String divisionCode = strArr[0];
            Integer divisionPoint = 0;
            Integer totalPoint = 0;
            for (int i = 1; i < strArr.length; i++) {
                totalPoint += Integer.parseInt(strArr[i]);
            }
            for (Condition condition : conditionList) {
                if (totalPoint < condition.getTotalPoint()) {
                    continue;
                }
                if (condition.getDivisionCode().equals(divisionCode)) {
                    for (Subject subject : subjectList) {
                        if (subject.getDivisionCode() != null && subject.getDivisionCode().equals(condition.getDivisionCode())) {
                            divisionPoint += Integer.parseInt(strArr[subjectOrdinalMap.get(subject.getCode())]);
                        }
                    }
                    if (divisionPoint >= condition.getDivisionPoint()) {
                        result++;
                    }
                    break;
                }
            }
            count++;
        }
        return result;
    }
}
