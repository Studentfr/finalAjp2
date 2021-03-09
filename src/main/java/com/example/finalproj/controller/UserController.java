package com.example.finalproj.controller;

import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Answer;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.service.AccountDetailsService;
import com.example.finalproj.service.QuestionService;
import com.example.finalproj.service.RoleService;
import com.example.finalproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AccountDetailsService accountDetailsService;
    private QuestionService questionService;
    private RoleService roleService;
    @Autowired
    public UserController(UserService userService, AccountDetailsService accountDetailsService, QuestionService questionService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.questionService = questionService;
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
        model.addAttribute("question", questionService.getAllQuestions());
        model.addAttribute("user", accountDetailsService.getAccount());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }
    @GetMapping("/addUser")
    public String toAddUser(Model model){
        model.addAttribute("user", new Account());
        model.addAttribute("roles", roleService.getRoles());
        return "addUser";
    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") Account account) {
        account.setRole(roleService.getRole(account.getRole().getRoleId()));
        userService.registerUser(account);
        return "redirect:/";
    }
    @GetMapping("/addQuestion")
    public String toAddQuestion(Model model){
        Question q = new Question();
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            answers.add(new Answer());
        }
        q.setAnswerOptions(answers);
        model.addAttribute("question", q);
        return "addQuestion";
    }
    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute("question") Question question) {
        questionService.registerQuestion(question);
        questionService.registerAnswers(question);
        return "redirect:/";
    }
    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(value = "id")Long id, Model model){
        questionService.deleteQuestion(id);
        return "redirect:/";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id")Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}
