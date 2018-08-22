package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EntityWithEnum;
import com.example.demo.entity.UserType;
import com.example.demo.repository.EntityWithEnumRepository;

@RestController
@RequestMapping(path = "/entityWithEnum")
public class EntityWithEnumController {

	@Autowired
	private EntityWithEnumRepository repository;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public EntityWithEnum createEntityWithEnum(@RequestBody EntityWithEnum entityWithEnum) {
		return repository.save(entityWithEnum);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<EntityWithEnum> listEntityWithEnum() {
		return repository.findAll();
	}

	@RequestMapping(value = "/bitwise", method = RequestMethod.GET)
	public List<EntityWithEnum> listVisiableEntityWithEnum(@RequestParam UserType type) {
		return repository.listEntityWithEnumByType(type.getValue());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EntityWithEnum getEntityWithEnumById(@PathVariable String id) {
		return repository.findOne(Long.valueOf(id));
	}
}
