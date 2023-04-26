package com.springboot.restapi.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class helloWorldResource {

    @RequestMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @RequestMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
    @RequestMapping("/hello-world-path-param/{name}")
    public HelloWorldBean helloWordPathParam(@PathVariable String name){
        return new HelloWorldBean("Hello World," + name);
    }

    @RequestMapping("/hello-world-path-param/{name}/message/{message}")
    public HelloWorldBean helloWordMultiplePathParam(@PathVariable String name, @PathVariable String message){
        return new HelloWorldBean("Hello World," + name +"= you message is " + message);

    }
}
