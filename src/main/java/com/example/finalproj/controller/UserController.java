package com.example.finalproj.controller;

import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String toUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "profile";
    }
}
