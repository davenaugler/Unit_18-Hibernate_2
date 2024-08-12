package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.domain.Account;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.repo.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Transactional
    public Account createAccountForUser(User user) {
        Account account = new Account();
        account.setAccountName("Account #" + (user.getAccounts().size() + 1));
        account.getUsers().add(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    public Account findAccountById(Long accountId) {
        return accountRepo.findById(accountId).orElse(null);
    }

    @Transactional
    public void updateAccountName(Long accountId, String accountName) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.setAccountName(accountName);
            accountRepo.save(account);
        }
    }
}
