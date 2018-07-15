package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.VersionedEntity;
import com.example.demo.entity.VersionedEntityPk;
import com.example.demo.service.VersionedEntityService;

@RestController
@RequestMapping(path = "/VersionedEntities")
public class VertionedEntityController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VersionedEntityService versionedEntityService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<VersionedEntity> listVersionedEntity() {
		return versionedEntityService.findAll();
	}

	@RequestMapping(path = "/id/{id:.+}", method = RequestMethod.GET)
	public List<VersionedEntity> listVersionedEntityById(@PathVariable Long id) {
		return versionedEntityService.findVersionedEntityById(id);
	}

	@RequestMapping(path = "/id/{id:.+}/version/{version:.+}", method = RequestMethod.GET)
	public VersionedEntity getVersionedEntityByIdAndVersion(@PathVariable Long id, @PathVariable double version) {
		logger.info("getVersionedEntityByIdAndVersion, id:{}, version:{}", id, version);
		VersionedEntityPk pk = new VersionedEntityPk(id, version);
		return versionedEntityService.findOne(pk);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public VersionedEntity createVersionedEntity(@RequestBody VersionedEntity entity) {
		return versionedEntityService.save(entity);
	}

}
