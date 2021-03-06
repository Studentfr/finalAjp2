package com.example.finalproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String toHome(){
        return "home";
    }

    @GetMapping("/register")
    public String toAddUser(){
        return "addUser";
    }
}
