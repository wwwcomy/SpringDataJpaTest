package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PlainTable;
import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.repository.PlainTableRepotistory;

@RestController
@RequestMapping(value = "/plainTables")
public class PlainTableController {

    @Autowired
    PlainTableRepotistory plainTableRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PlainTable> listAll() {
        return plainTableRepository.findAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public PlainTable findByName(@PathVariable String name) {
        return plainTableRepository.findOne(name);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public PlainTable updateName(@PathVariable String name, @RequestBody PlainTable newPlainTable) {
        PlainTable existing = plainTableRepository.findOne(name);
        PlainTable check = plainTableRepository.findOne(newPlainTable.getPrefix());
        if (check != null) {
            throw new EntityAlreadyExistsException();
        }
        plainTableRepository.delete(existing);
        return plainTableRepository.save(newPlainTable);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public PlainTable savePlainTable(@RequestBody PlainTable newPlainTable) {
        PlainTable check = plainTableRepository.findOne(newPlainTable.getPrefix());
        if (check != null) {
            throw new EntityAlreadyExistsException();
        }
        return plainTableRepository.save(newPlainTable);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void delName(@PathVariable String name) {
        plainTableRepository.delete(name);
    }
}
