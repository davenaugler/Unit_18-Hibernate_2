package com.coderscampus.Unit_18_Hibernate_2.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String name;

    @ManyToMany
    @JoinTable(name = "user_account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Address getAddress() {
        return address;
    }

    // Ensures bidirectional relationship is maintained
    public void setAddress(Address address) {
        if (address == null) {
            if (this.address != null) {
                this.address.setUser(null);
            }
        } else {
            address.setUser(this);
        }
        this.address = address;
    }

    // REVISED toString() via Claude
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                ", addressId=" + (address != null ? address.getUserId() : "null") +
                '}';
    }


//    ORIGINAL to String()
//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", name='" + name + '\'' +
//                ", accounts=" + accounts +
//                ", address=" + address +
//                '}';
//    }
}
