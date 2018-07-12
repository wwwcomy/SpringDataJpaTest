package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LogicGroup;

/**
 * @Date 2018/04/13
 */
@Repository
public interface LogicGroupRepository extends JpaRepository<LogicGroup, Long> {

    @Query(value = "select l from LogicGroup l join l.groupParams p where KEY(p) = :paramKey and p IN :paramValues ")
    List<LogicGroup> findByParamValueIn(@Param(value = "paramKey") String key,
        @Param(value = "paramValues") List<String> paramValues);

    @Query(value = "select l from LogicGroup l join l.groupParams p where KEY(p) = :paramKey and p = :paramValue ")
    List<LogicGroup> findByParamValue(@Param(value = "paramKey") String key,
        @Param(value = "paramValue") String paramValue);
}