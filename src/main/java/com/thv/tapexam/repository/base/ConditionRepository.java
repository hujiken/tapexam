package com.thv.tapexam.repository.base;

import com.thv.tapexam.entity.Condition;
import com.thv.tapexam.repository.custom.ConditionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConditionRepository extends JpaRepository<Condition, Integer>, ConditionRepositoryCustom {
    Optional<Condition> findByName(String code);

    List<Condition> findAllByEnable(Boolean enable);
}
