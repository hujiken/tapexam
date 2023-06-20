package com.thv.tapexam.service.base;

import com.thv.tapexam.dto.request.SubjectRequest;
import com.thv.tapexam.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<Subject> findById(Integer subjectId) throws Exception;

    Optional<List<Subject>> getAllSubject();

    Boolean addSubject(SubjectRequest subjectRequest) throws Exception;

    Boolean updateSubject(Integer subjectId, SubjectRequest subjectRequest) throws Exception;

    Boolean deleteSubject(Integer subjectId) throws Exception;
}
