package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
            value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
            value = "Number of records per page.", defaultValue = "10"),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
            value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. "
                + "Multiple sort criteria are supported.",
            defaultValue = "id,desc")})
    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public Page<User> getUserByName(@PathVariable String name, @ApiIgnore @PageableDefault(size = 15,
        sort = {"createdTime"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        return userRepository.findByName(name, pageable);
    }
}
