package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OneToOneWithChildrenEntity;
import com.example.demo.repository.OneToOneWithChildrenRepository;

@RestController
@RequestMapping(value = "/oneToOneWithChildrenEntities")
public class OneToOneWithChildrenController {

	@Autowired
	OneToOneWithChildrenRepository repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public OneToOneWithChildrenEntity getById(@RequestParam String id) {
		return repository.findOneToOneWithChildren(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public OneToOneWithChildrenEntity create(@RequestBody @Valid OneToOneWithChildrenEntity entity) {
		return repository.save(entity);
	}

}
