package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController  {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
       List<User> users = userService.findALl();
        model.put("users", users);
        return "users";
    }
}

