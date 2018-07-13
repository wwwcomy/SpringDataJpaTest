package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.VersionedEntity;
import com.example.demo.entity.VersionedEntityPk;
import com.example.demo.repository.VersionedEntityRepository;

@Service
public class VersionedEntityService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VersionedEntityRepository versionedEntityRepository;

	public List<VersionedEntity> findAll() {
		return versionedEntityRepository.findAll();
	}

	public List<VersionedEntity> findVersionedEntityById(Long id) {
		return versionedEntityRepository.findVersionedEntityByPkId(id);
	}

	public VersionedEntity save(VersionedEntity entity) {
		return versionedEntityRepository.save(entity);
	}

	public VersionedEntity findOne(VersionedEntityPk id) {
		logger.info("Searching for versionedEntity using PK:{}", id);
		return versionedEntityRepository.findOne(id);
	}

}
