package com.example.finalproj.controller;

import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AccountDetailsService accountDetailsService;
    @Autowired
    public UserController(UserService userService, AccountDetailsService accountDetailsService) {
        this.userService = userService;
        this.accountDetailsService = accountDetailsService;
    }

    @GetMapping("/{id}")
    public String toUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "profile";
    }
    @GetMapping("/{id}/edit")
    public String updateUser(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("user", new Account());
        return "updateUser";
    }
    @PostMapping("/{id}/edit")
    public String updateUser(@ModelAttribute("user") Account user, @PathVariable(value = "id") Long id){
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String toAdmin(Model model){
        model.addAttribute("user", accountDetailsService.getAccount());
        return "admin";
    }
}
