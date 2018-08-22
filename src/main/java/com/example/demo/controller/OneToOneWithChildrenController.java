package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

}
