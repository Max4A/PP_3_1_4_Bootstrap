package com.example.spring_security.controllers;

import com.example.spring_security.entities.User;
import com.example.spring_security.services.RoleService;
import com.example.spring_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private UserService userService;
    private RoleService roleService;
//    private PasswordEncoder passwordEncoder;
    @Autowired
    public MainController(UserService userService, RoleService roleService/*, PasswordEncoder passwordEncoder*/) {
        this.userService = userService;
        this.roleService = roleService;
//        this.passwordEncoder = passwordEncoder;
    }

// ######## Общая страница #############
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // ######## Страница юзера #############
    @GetMapping("/user")
    public String pageForUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    // ######## Страница админа - все юзеры #############
    @GetMapping("/admin")
    public String pageForAdmin(Principal principal, Model model) {  // все юзеры
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }
//  ############### new user ####################################

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "addUser";
    }

    @PostMapping("/admin/save")
    public String createUser(@ModelAttribute("user") User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        return "redirect:/admin";
    }

// ############### delete user ############################################################

    @DeleteMapping("/admin/{id}")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

// ############### edit user ############################################################
    @GetMapping("/admin/{username}/edit")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleService.allRoles());
        return "editUser";
    }

    @PatchMapping("/admin/{username}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("username") String username) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(username, user);
        return "redirect:/admin";
    }
}
