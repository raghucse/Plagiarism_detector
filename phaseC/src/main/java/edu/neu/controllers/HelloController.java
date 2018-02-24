package edu.neu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/api/hello/string")
    public String sayHello() {
        return "Hello World!";
    }

    @Autowired
    HelloRepository helloRepository;

    @RequestMapping("/api/hello/insert")
    public HelloObject insertHelloObject() {
        HelloObject obj = new HelloObject("Hello Raghu!");
        helloRepository.save(obj);
        return obj;
    }

    @RequestMapping("/api/hello/insert/{message}")
    public HelloObject insertMessageHelloObject(@PathVariable("message") String message) {
        HelloObject obj = new HelloObject(message);
        helloRepository.save(obj);
        return obj;
    }

    @RequestMapping("/api/hello/select/all")
    public List<HelloObject> selectAllHelloObjects() {
        List<HelloObject> hellos =
                (List<HelloObject>)helloRepository.findAll();

        return hellos;
    }

}
