package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.domain.Account;
import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.AccountRepository;
import com.coderscampus.Unit_18_Hibernate_2.repo.AddressRepository;
import com.coderscampus.Unit_18_Hibernate_2.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AccountRepository accountRepo;
    private final AddressRepository addressRepo;

    public UserService(UserRepository userRepo, AddressRepository addressRepo, AccountRepository accountRepo) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.accountRepo = accountRepo;
    }

    public List<User> findALlUsers() {
        return userRepo.findAll();
    }

    public User findOneUserById(Long userId) {
        Optional<User> userOpt = userRepo.findByIdWithAddress(userId);
        return userOpt.orElse(new User());
    }

    @Transactional
    public User saveUser(User user, Address newAddress) {
        if (user.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User existingUser = findOneUserById(user.getUserId());
        if (existingUser == null) {
            throw new RuntimeException("User not found with id: " + user.getUserId());
        }

        // Update user fields if necessary
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());

        // Update or create address
        if (existingUser.getAddress() == null) {
            existingUser.setAddress(newAddress);
        } else {
            Address existingAddress = existingUser.getAddress();
            existingAddress.setAddressLine1(newAddress.getAddressLine1());
            existingAddress.setAddressLine2(newAddress.getAddressLine2());
            existingAddress.setCity(newAddress.getCity());
            existingAddress.setRegion(newAddress.getRegion());
            existingAddress.setCountry(newAddress.getCountry());
            existingAddress.setZipCode(newAddress.getZipCode());
        }

        return userRepo.save(existingUser);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }
}
