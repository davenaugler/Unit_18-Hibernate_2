package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.AccountRepository;
import com.coderscampus.Unit_18_Hibernate_2.service.AddressService;
import com.coderscampus.Unit_18_Hibernate_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
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
    public String getAllUsers(@RequestParam(required = false) Boolean includeAccounts, ModelMap model) {
        List<User> users;
        if (Boolean.TRUE.equals(includeAccounts)) {
            users = userService.findAllUsersWithAccounts();
        } else {
            users = userService.findAllUsers();
        }
        model.put("users", users);
        model.put("includeAccounts", includeAccounts);
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser(@PathVariable Long userId, ModelMap model) {
        System.out.println("************** INSIDE getOneUser() **************");
        User user = userService.findOneUserById(userId);
        if (user == null) {
            // Handle user not found scenario
            return "redirect:/error";
        }

        Address address = user.getAddress();
        if (address == null) {
            address = new Address();
            address.setUser(user);
            System.out.println("<---------> Inside `getOneUser()` address: " + address);
            System.out.println("<---------> Inside `getOneUser()` user: " + user);
        }

        System.out.println("`getOneUser()`: " + userId);
        System.out.println("`getOneUser()`: " + user);
        System.out.println("`getOneUser()`: " + address);
        System.out.println("************** DONE WITH getOneUser() **************");
        model.put("users", Arrays.asList(user));
        model.put("user", user);
        model.put("address", address);
        return "users";
    }

    @PostMapping("/users/{userId}")
    public String postOneUser(@PathVariable Long userId,
                              @ModelAttribute User user,
                              @ModelAttribute Address address) {
        // Ensure the userId from the path matches the user object
        if (!userId.equals(user.getUserId())) {
            return "redirect:/error";
        }

        System.out.println("************ BEFORE `postOneUser` ************");
        System.out.println("BEFORE `postOneUser`: " + userId);
        System.out.println("BEFORE `postOneUser`: " + user);
        System.out.println("BEFORE `postOneUser`: " + address);

        User savedUser = userService.saveUser(user, address);

        System.out.println("AFTER `postOneUser`: " + userId);
        System.out.println("AFTER `postOneUser`: " + user);
        System.out.println("AFTER `postOneUser`: " + address);
        System.out.println("AFTER `postOneUser`: " + savedUser);
        System.out.println("AFTER `postOneUser`: " + savedUser.getAddress());

        System.out.println("************ DONE WITH `postOneUser` ************");

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
        System.out.println("************** INSIDE postCreateUser() **************");
        System.out.println("BEFORE user gets Primary Key: " + user);
        userService.createUser(user);
        System.out.println("AFTER user gets Primary Key" + user);
        System.out.println("************** DONE WITH postCreateUser() **************");
        return "redirect:/register";
    }

    @PostMapping("/users/{userId}/delete")
    public String deleteOneUser(@PathVariable Long userId, User user) {
        userService.delete(userId);
        return "redirect:/users";
    }

}

