package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // user.html
    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        List<User> users = userService.findALlUsers();
        model.put("users", users);
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser(@PathVariable Long userId, ModelMap model) {
        User user = userService.findOneUserById(userId);
        model.put("users", Arrays.asList(user));
        model.put("user", user);
        return "users";
    }

    //    register.html
    @GetMapping("/register")
    public String createUser(ModelMap model) {
//        LONG WAY
//        User user = new User();
//        model.put("user", user);

//        SHORT WAY
        model.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser(User user) {
        System.out.println("BEFORE user gets Primary Key: " + user);
        userService.createUser(user);
        System.out.println("AFTER user gets Primary Key" + user);
        return "redirect:/register";
    }


}

