package com.example.lab_3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Controller
public class MainController {

    @RequestMapping(value = "/index")
    private String index(){
        return "index";
    }
}
