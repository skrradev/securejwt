package com.zhan.securejwt.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class SimpleController {

    @GetMapping("/hello")
    public String hello () {

        return "SCREW YOU!";
    }

}
