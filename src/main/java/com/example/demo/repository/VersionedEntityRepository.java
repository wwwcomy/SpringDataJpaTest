package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VersionedEntity;
import com.example.demo.entity.VersionedEntityPk;

@Repository
public interface VersionedEntityRepository extends JpaRepository<VersionedEntity, VersionedEntityPk> {

	List<VersionedEntity> findVersionedEntityByPkId(Long id);
}
