package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.domain.Account;
import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.AccountRepository;
import com.coderscampus.Unit_18_Hibernate_2.repo.AddressRepository;
import com.coderscampus.Unit_18_Hibernate_2.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public List<User> findAllUsersWithAccounts() {
        return userRepo.findAllWithAccounts();
    }

    public User findOneUserById(Long userId) {
        Optional<User> userOpt = userRepo.findByIdWithAddress(userId);
        return userOpt.orElse(new User());
    }

//   public List<User> findByUsername(String username) {
//        return userRepo.findByUsername(username);
//
//   }
//
//   public List<User> findByName(String name) {
//        return userRepo.name();
//
//   }
//
//   public List<User> findByUsernameAndName(String username, String name) {
//        return userRepo.findByUsernameAndName(username, name);
//
//   }
//
//   public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
//        return userRepo.findByCreatedDateBetween(date1, date2);
//
//   }




    // Plan to clean this up, break it up to multiple methods, and or refactor.
//    @Transactional
//    public User saveUser(User user, Address newAddress) {
//        if (user.getUserId() == null) {
//            throw new IllegalArgumentException("User ID must not be null");
//        }
//
//        User existingUser = findOneUserById(user.getUserId());
//        if (existingUser == null) {
//            throw new RuntimeException("User not found with id: " + user.getUserId());
//        }
//
//        // Update user fields if necessary
//        existingUser.setUsername(user.getUsername());
//        existingUser.setPassword(user.getPassword());
//        existingUser.setName(user.getName());
//
//        // Update or create address
//        Address existingAddress = existingUser.getAddress();
//        if (existingAddress == null) {
//            existingAddress = new Address();
//            existingAddress.setUser(existingUser);
//            existingUser.setAddress(existingAddress);
//        }
//
//        // Update address fields
//        existingAddress.setAddressLine1(newAddress.getAddressLine1());
//        existingAddress.setAddressLine2(newAddress.getAddressLine2());
//        existingAddress.setCity(newAddress.getCity());
//        existingAddress.setRegion(newAddress.getRegion());
//        existingAddress.setCountry(newAddress.getCountry());
//        existingAddress.setZipCode(newAddress.getZipCode());
//
//        // Save the user (which will cascade to the address)
//        return userRepo.save(existingUser);
//
////        // Update or create address
////        if (existingUser.getAddress() == null) {
////            existingUser.setAddress(newAddress);
////        } else {
////            Address existingAddress = existingUser.getAddress();
////            existingAddress.setAddressLine1(newAddress.getAddressLine1());
////            existingAddress.setAddressLine2(newAddress.getAddressLine2());
////            existingAddress.setCity(newAddress.getCity());
////            existingAddress.setRegion(newAddress.getRegion());
////            existingAddress.setCountry(newAddress.getCountry());
////            existingAddress.setZipCode(newAddress.getZipCode());
////        }
////        return userRepo.save(existingUser);
//    }

    @Transactional
    public User saveUser(User formUser, Address formAddress) {
        User existingUser = findOneUserById(formUser.getUserId());
        if (existingUser == null) {
            throw new RuntimeException("User not found with id: " + formUser.getUserId());
        }

        // Update user fields
        existingUser.setUsername(formUser.getUsername());
        existingUser.setPassword(formUser.getPassword());
        existingUser.setName(formUser.getName());

        // Update or create address
        Address existingAddress = existingUser.getAddress();
        if (existingAddress == null) {
            existingAddress = new Address();
            existingAddress.setUser(existingUser);
            existingUser.setAddress(existingAddress);
        }

        // Update address fields
        existingAddress.setAddressLine1(formAddress.getAddressLine1());
        existingAddress.setAddressLine2(formAddress.getAddressLine2());
        existingAddress.setCity(formAddress.getCity());
        existingAddress.setRegion(formAddress.getRegion());
        existingAddress.setCountry(formAddress.getCountry());
        existingAddress.setZipCode(formAddress.getZipCode());

        // Save the user (which will cascade to the address)
        return userRepo.save(existingUser);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

//    public Account createAccount(Long accountId) {
//        return accountRepo.save(accountId);
//    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }
}
