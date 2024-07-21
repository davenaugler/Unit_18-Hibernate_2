package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.repo.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepo;

    public AddressService(AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }
}
