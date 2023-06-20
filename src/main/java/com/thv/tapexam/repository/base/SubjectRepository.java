package com.thv.tapexam.repository.base;

import com.thv.tapexam.entity.Subject;
import com.thv.tapexam.repository.custom.SubjectRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer>, SubjectRepositoryCustom {
    Optional<Subject> findByNameOrCode(String name, String code);
}
