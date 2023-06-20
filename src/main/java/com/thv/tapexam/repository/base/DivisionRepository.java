package com.thv.tapexam.repository.base;

import com.thv.tapexam.entity.Division;
import com.thv.tapexam.repository.custom.DivisionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DivisionRepository extends JpaRepository<Division, Integer>, DivisionRepositoryCustom {
    Optional<Division> findByNameOrCode(String name, String code);
}
