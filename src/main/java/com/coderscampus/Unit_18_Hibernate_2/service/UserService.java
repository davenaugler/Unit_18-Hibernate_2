package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findALl() {
        return userRepo.findAll();
    }
}
