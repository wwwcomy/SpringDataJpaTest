package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.advice.Response;

@RestController
@RequestMapping(path = "/exceptions")
public class ExceptionController {

    @RequestMapping(path = "/{exceptionId}", method = RequestMethod.GET)
    public Response getException(@PathVariable String exceptionId) {
        throw new RuntimeException();
    }

}
