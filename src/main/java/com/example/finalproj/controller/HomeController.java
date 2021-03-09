package com.example.finalproj.controller;

import com.example.finalproj.repository.RoleRepository;
import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.repository.dto.Vote;
import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.QuestionService;
import com.example.finalproj.service.RoleService;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private RoleService roleService;
    private AccountDetailsService accountDetailsService;
    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public HomeController(RoleService roleService, AccountDetailsService accountDetailsService, UserService userService, QuestionService questionService) {
        this.roleService = roleService;
        this.questionService = questionService;
        this.accountDetailsService = accountDetailsService;
        this.userService = userService;
    }


    @GetMapping
    public String toHome(Model model){
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("user", accountDetailsService.getAccount());
        model.addAttribute("question", questionService.getAllQuestions());
        model.addAttribute("answers", questionService.getAllAnswers());
        model.addAttribute("chosenAns", new Answer());
        return "home";
    }

    @PostMapping("/home")
    public String answer(@ModelAttribute("chosenAns") Answer ans){
        questionService.setVote(ans.getAnswerId(),accountDetailsService.getAccount());
        return "redirect:/";
    }

}