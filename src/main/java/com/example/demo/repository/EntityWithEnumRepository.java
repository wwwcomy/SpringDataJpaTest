package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntityWithEnum;

@Repository
public interface EntityWithEnumRepository extends JpaRepository<EntityWithEnum, Long> {

	// @Query(value = "select * from entity_with_enum t_1 where BitAnd(t_1.ut,
	// :type)>0", nativeQuery = true)
	@Query(value = "select * from entity_with_enum t_1 where t_1.user_type&:type>0", nativeQuery = true)
	List<EntityWithEnum> listEntityWithEnumByType(@Param(value = "type") int type);
}