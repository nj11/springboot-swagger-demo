package com.springboot.swagger.demo.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class IndexController {
    @RequestMapping(method= RequestMethod.GET)
    String index(){
        return "index";
    }
}
