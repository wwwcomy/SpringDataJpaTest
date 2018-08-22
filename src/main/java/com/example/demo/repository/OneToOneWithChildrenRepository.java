package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OneToOneWithChildrenEntity;

@Repository
public interface OneToOneWithChildrenRepository extends JpaRepository<OneToOneWithChildrenEntity, String> {

	@Query(value = "select * from one_to_one_with_children_entity t_1 where t_1.id =:id", nativeQuery = true)
	OneToOneWithChildrenEntity findOneToOneWithChildren(@Param(value = "id") String id);
}