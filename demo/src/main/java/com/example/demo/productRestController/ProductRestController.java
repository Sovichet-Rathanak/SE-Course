package com.example.demo.productRestController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/TP04")
public class ProductRestController {

    private ArrayList<Users> userList = new ArrayList<>();

    public ProductRestController() {
        userList.add(new Users("Sovichet Rathanak", "12345"));
    }

    @GetMapping("/task1")
    public String getTask1(Model model) {
        model.addAttribute("Welcome", "Welcome To TP04 Task1");
        return "task/TP1";
    }

    @GetMapping("/task2")
    public String getTask2(Model model) {
        return "task/TP2";
    }

    @GetMapping("/task3")
    public String getTask3(Model model) {
        return "task/TP3";
    }

    @GetMapping("/task4")
    public String getTask4(Model model) {
        return "task/TP4";
    }

    @GetMapping("/task5")
    public String getTask5(Model model) {
        model.addAttribute("UserList", userList);
        return "task/TP5";  
    }

    //Error 500
    @PostMapping("/task5")
    public String postTask5(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isValidUser = Users.validateUser(userList, username, password);
        if (isValidUser) {
            return "redirect:/LogInSuccess";
        } else {
            return "redirect:/LoginFailed";
        }
    }   
}
