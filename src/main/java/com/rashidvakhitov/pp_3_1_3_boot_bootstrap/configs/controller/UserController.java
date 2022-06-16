package com.rashidvakhitov.pp_3_1_3_boot_bootstrap.configs.controller;

import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.model.User;
import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Principal principal, Model model) {
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
