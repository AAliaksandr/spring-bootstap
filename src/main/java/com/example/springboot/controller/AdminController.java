package com.example.springboot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUserName(currentUser.getUsername());
        List<User> list = userService.getAllUsers();
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("roles", user.getRoleByUser());
        model.addAttribute("user", user);
        model.addAttribute("users", list);
        model.addAttribute("listRoles", listRoles);
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(Model model, @ModelAttribute("addUser") User user,
                           @RequestParam(value = "role", required = false) String[] role) {
        model.addAttribute("user", user);
        Set<Role> roles = new HashSet<>();
        for (String s : role) {
            roles.add(roleService.findRoleByRoleName(s));
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/edit")
    public String editUser(Model model, @ModelAttribute("user") User user,
                           @RequestParam(value = "role", required = false) String[] role){
        model.addAttribute("user", user);
        Set<Role> roles = new HashSet<>();
        for (String s : role) {
            roles.add(roleService.findRoleByRoleName(s));
        }
        user.setRoles(roles);
        userService.edit(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(userService.getUser(id));
        return "redirect:/admin/users/";
    }
}
