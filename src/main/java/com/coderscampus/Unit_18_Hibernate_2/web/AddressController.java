package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Brought over from UserController.java
//    @GetMapping("/users/{userId}")
//    public String getOneUser(@PathVariable Long userId, ModelMap model) {
//        User user = userService.findOneUserById(userId);
//        model.put("users", Arrays.asList(user));
//        model.put("user", user);
//        return "users";
//    }


}
