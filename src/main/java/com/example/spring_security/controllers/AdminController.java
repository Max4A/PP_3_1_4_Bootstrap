//package com.example.spring_security.controllers;
//
//import com.example.spring_security.entities.User;
//import com.example.spring_security.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.security.Principal;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    private UserService userService;
//    @Autowired
//    public AdminController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping()
//    public String pageForAdmin(Principal principal, Model model) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        return "admin";
//    }
//}
