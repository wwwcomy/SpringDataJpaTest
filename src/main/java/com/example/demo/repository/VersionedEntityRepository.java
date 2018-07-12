package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VersionedEntityPk;
import com.example.demo.entity.VersionedEntity;

@Repository
public interface VersionedEntityRepository extends JpaRepository<VersionedEntity, VersionedEntityPk> {}
