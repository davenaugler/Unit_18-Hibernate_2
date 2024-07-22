package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.service.AddressService;
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
    private final AddressService addressService;

    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
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
        Address address = addressService.findOneAddressById(userId);
        model.put("users", Arrays.asList(user));
        model.put("user", user);
        model.put("address", address != null ? address : new Address());
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

    @PostMapping("/users/{userId}/delete")
    public String deleteOneUser(@PathVariable Long userId, User user) {
        userService.delete(userId);
        return "redirect:/users";
    }


}

