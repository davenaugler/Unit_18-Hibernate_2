package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.domain.Address;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.AddressRepository;
import com.coderscampus.Unit_18_Hibernate_2.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AddressRepository addressRepo;

    public UserService(UserRepository userRepo, AddressRepository addressRepo) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }

    public List<User> findALlUsers() {
        return userRepo.findAll();
    }

    public User findOneUserById(Long userId) {
        Optional<User> userOpt = userRepo.findByIdWithAddress(userId);
        return userOpt.orElse(new User());
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }
}
