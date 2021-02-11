package com.example.springboot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public String getInfoUser(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUserName(currentUser.getUsername());
        model.addAttribute("user" , user);
        model.addAttribute("roles", user.getRoleByUser());
        return "user";
    }
}
