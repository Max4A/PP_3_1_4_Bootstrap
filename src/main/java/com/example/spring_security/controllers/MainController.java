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
//    private RoleRepository roleRepository;
//    private UserRepository userRepository;

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public MainController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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

    // ######## Страница админа #############
    @GetMapping("/admin")
    public String pageForAdmin(Principal principal, Model model) {  // все юзеры
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }
//  new user ####################################

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "addUser";
    }

    @PostMapping("/admin/save")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "select-role") String[] roleNames) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.getRoleByNames(roleNames));
        userService.saveUser(user);

        return "redirect:/admin";
    }

// delete user ############################################################

    @DeleteMapping("/admin/{id}")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @GetMapping("/newAddUserAdmin")
//    public String addNewUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//
//        List<Role> roles = roleService.getList();
//        model.addAttribute("roleList", roles);
////        model.addAttribute("roleList", roles);
//        return "user_new_admin";
//    }
//
//    @PostMapping("/newAddUserAdmin")
//    public String saveNewUser(
//            @ModelAttribute("user") User user
//    ) {
////        List<String> lsr = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
////        List<Role> liRo = roleService.listByRole(lsr);
////
////        user.setRoles(liRo);
//        //user.setRoles(roles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.add(user);
//        return "redirect:/admin";
//    }

    // ##################################################
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", service.getUserById(id));
//        return "editUser";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user,
//                         @PathVariable("id") Long id) {
//        service.updateUser(id, user);
//        return "redirect:/users/all";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@ModelAttribute("user") User user,
//                         @PathVariable("id") Long id) {
//        service.deleteUser(id);
//        return "redirect:/users/all";
//    }



}
