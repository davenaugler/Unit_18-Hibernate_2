package com.coderscampus.Unit_18_Hibernate_2.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(length = 100)
    private String accountName;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();
    @ManyToMany(mappedBy = "accounts")
    private List<User> users = new ArrayList<>();

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "accountId=" + accountId +
//                ", accountName='" + accountName + '\'' +
//                ", transactions=" + transactions +
//                ", users=" + users +
//                '}';
//    }

    // Added 7/26/2024 @ 3:28pm
    // Avoids the infinite recursion problem by not directly calling toString()
    // on the collections for transactions and users.
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", transactions=" + (transactions != null ? "[" + transactions.size() + " transactions]" : "null") +
                ", users=" + (users != null ? "[" + users.size() + " users]" : "null") +
                '}';
    }
}
