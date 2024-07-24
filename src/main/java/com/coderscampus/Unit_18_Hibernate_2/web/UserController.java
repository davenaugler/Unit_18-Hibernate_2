package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.AccountRepository;
import com.coderscampus.Unit_18_Hibernate_2.service.AddressService;
import com.coderscampus.Unit_18_Hibernate_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final AddressService addressService;
    private final AccountRepository accountRepo;

    public UserController(UserService userService, AddressService addressService, AccountRepository accountRepo) {
        this.userService = userService;
        this.addressService = addressService;
        this.accountRepo = accountRepo;
    }

    // users.html
    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        List<User> users = userService.findALlUsers();
        model.put("users", users);
        return "users";
    }

    // users.html
//    @GetMapping("/users/{userId}")
//    public String getOneUser(@PathVariable Long userId, ModelMap model) {
//        User user = userService.findOneUserById(userId);
//        Address address = addressService.findOneAddressById(userId);
//        model.put("users", Arrays.asList(user));
//        model.put("user", user);
//        model.put("address", address != null ? address : new Address());
//        return "users";
//    }

    @GetMapping("/users/{userId}")
    public String getOneUser(@PathVariable Long userId, ModelMap model) {
        User user = userService.findOneUserById(userId);
        if (user == null) {
            // Handle user not found scenario
            return "redirect:/error";
        }
        Address address = user.getAddress();
        if (address == null) {
            address = new Address();
            address.setUser(user);
        }
        model.put("users", Arrays.asList(user));
        model.put("user", user);
        model.put("address", address);
        return "users";
    }

    // users.html
    @PostMapping("/users/{userId}")
    public String postOneUser(@PathVariable Long userId,
                              @ModelAttribute User user,
                              @ModelAttribute Address address) {
        // Ensure the userId from the path matches the user object
        if (!userId.equals(user.getUserId())) {
            // Handle mismatch - perhaps return an error page or redirect
            return "redirect:/error";
        }
        System.out.println("BEFORE `postOneUser`: " + userId);
        System.out.println("BEFORE `postOneUser`: " + user);
        System.out.println("BEFORE `postOneUser`: " + address);
        user.setUserId(userId);
        userService.saveUser(user, address);
        System.out.println("AFTER `postOneUser`: " + userId);
        System.out.println("AFTER `postOneUser`: " + user);
        System.out.println("AFTER `postOneUser`: " + address);
        return "redirect:/users/" + userId;
    }


    //    register.html
    @GetMapping("/register")
    public String createUser(ModelMap model) {
        model.put("user", new User());
        return "register";
    }

    //    register.html
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

