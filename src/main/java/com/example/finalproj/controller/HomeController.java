package com.example.finalproj.controller;

import com.example.finalproj.repository.RoleRepository;
import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private RoleService roleService;
    private AccountDetailsService accountDetailsService;

    @Autowired
    public HomeController(RoleService roleService, AccountDetailsService accountDetailsService) {
        this.roleService = roleService;
        this.accountDetailsService = accountDetailsService;
    }

    @GetMapping
    public String toHome(){
        return "home";
    }

    @GetMapping("/register")
    public String toAddUser(Model model){
        model.addAttribute("user", new Account());
        model.addAttribute("roles", roleService.getRoles());
        return "addUser";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") Account account) {
        account.setRole(roleService.getRole(account.getRole().getRoleId()));
        accountDetailsService.registerUser(account);
        return "redirect:/";
    }


}
