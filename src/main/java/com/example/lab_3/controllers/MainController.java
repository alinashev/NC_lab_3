package com.example.lab_3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/index")
    public String hello(){
        return "Hello";
    }
}
