package edu.neu.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/api/hello/{message}")
    public String insertMessageHelloObject(@PathVariable("message") String message) {
        return "Hello "+message+"!";
    }

}
