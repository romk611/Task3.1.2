package com.example.task312.controllers;


import com.example.task312.model.Role;
import com.example.task312.model.User;
import com.example.task312.service.RoleService;
import com.example.task312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder encoder;

    @Autowired
    public AdminsController(UserService userService, PasswordEncoder encoder, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }


    @GetMapping("/users")
    public String AllUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "index";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserW(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRole());
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam (value = "editRole") String[] roles1, @PathVariable("id") long id){
        User user1 = userService.getUserById(id);
        user.setPassword(user1.getPassword());
        userService.editUser(user);
        return "redirect:/admin/users";
    }
    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(required = false, value = "nameRoles") String[] roles) {
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "new";
    }
}
