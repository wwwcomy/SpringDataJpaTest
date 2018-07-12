package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.VersionedEntity;
import com.example.demo.entity.VersionedEntityPk;
import com.example.demo.repository.VersionedEntityRepository;

@RestController
@RequestMapping(path = "/VersionedEntities")
public class VertionedEntityController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VersionedEntityRepository versionedEntityRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<VersionedEntity> listVersionedEntity(VersionedEntityPk pk) {
        logger.info(pk.toString());
        return versionedEntityRepository.findAll();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public VersionedEntity createVersionedEntity(@RequestBody VersionedEntity entity) {
        return versionedEntityRepository.save(entity);
    }

    // @RequestMapping(path = "/", method = RequestMethod.GET)
    // public VersionedEntity createVersionedEntity(@RequestBody VersionedEntity entity) {
    // return versionedEntityRepository.save(entity);
    // }
}
