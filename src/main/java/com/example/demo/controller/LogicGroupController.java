package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LogicGroup;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.LogicGroupRepository;

@RestController
@RequestMapping(value = "/logicGroups")
public class LogicGroupController {

    @Autowired
    LogicGroupRepository logicGroupRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<LogicGroup> listLogicGroups() {
        return logicGroupRepository.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<LogicGroup> searchByParam(@RequestParam(required = false, name = "paramKey") String paramKey,
        @RequestParam(required = false, name = "paramValue") String paramValue) {
        return logicGroupRepository.findByParamValue(paramKey, paramValue);
    }

    @RequestMapping(value = "/search2", method = RequestMethod.GET)
    public List<LogicGroup> searchByParamIn(@RequestParam(required = false, name = "paramKey") String paramKey,
        @RequestParam(required = false, name = "paramValue") List<String> paramValues) {
        return logicGroupRepository.findByParamValueIn(paramKey, paramValues);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public LogicGroup getLogicGroupById(@PathVariable String id) {
        return logicGroupRepository.findOne(Long.valueOf(id));
    }

    @RequestMapping(value = "/{logicGroupId}", method = RequestMethod.PUT)
    public LogicGroup updateLogicGroup(@PathVariable String logicGroupId, @RequestBody LogicGroup logicGroup) {
        LogicGroup existingGroup = logicGroupRepository.findOne(Long.valueOf(logicGroupId));
        if (existingGroup == null) {
            throw new EntityNotFoundException("GroupId " + logicGroupId + " not found");
        }
        existingGroup.update(logicGroup);
        return logicGroupRepository.save(existingGroup);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public LogicGroup createLogicGroup(@RequestBody LogicGroup logicGroup) {
        return logicGroupRepository.save(logicGroup);
    }
}
