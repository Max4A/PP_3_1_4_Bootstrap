package com.example.spring_security.controllers;

import com.example.spring_security.entities.Role;
import com.example.spring_security.entities.User;
import com.example.spring_security.repositories.RoleRepository;
import com.example.spring_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private RoleRepository roleRepository;
    private UserService userService;
    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/user")
    public String pageForUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
//        List<Role> roles = roleRepository.findAll();
//        model.addAttribute("roles", roles);

        return "user";
    }




//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", service.getUserById(id));
//        return "editUser";


}
