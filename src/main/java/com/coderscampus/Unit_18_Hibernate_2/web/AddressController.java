package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.service.AddressService;
import org.springframework.stereotype.Controller;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

}
