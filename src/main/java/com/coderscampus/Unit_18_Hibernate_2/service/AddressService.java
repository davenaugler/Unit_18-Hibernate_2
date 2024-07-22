package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.repo.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepo;

    public AddressService(AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }

    public Address findOneAddressById(Long userId) {
        Optional<Address> addressOpt = addressRepo.findById(userId);
        return addressOpt.orElse(new Address());
    }

    public Address createAddress(Address address) {
        return addressRepo.save(address);
    }


}
