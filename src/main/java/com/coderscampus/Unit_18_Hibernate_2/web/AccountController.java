package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.Account;
import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import com.coderscampus.Unit_18_Hibernate_2.service.AccountService;
import com.coderscampus.Unit_18_Hibernate_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createAccount(@PathVariable Long userId) {
        User user = userService.findOneUserById(userId);
        if(user == null) {
            return "redirect:/error";
        }

        Account newAccount = accountService.createAccountForUser(user);
        return "redirect:/users/" + userId + "/accounts/" + newAccount.getAccountId();
    }

    @GetMapping("/{accountId}")
    public String showAccountDetails(@PathVariable Long userId, @PathVariable Long accountId, ModelMap model) {
        User user = userService.findOneUserById(userId);
        Account account = accountService.findAccountById(accountId);

        if (user == null || account == null || !user.getAccounts().contains(account)) {
            return "redirect:/error";
        }

        model.put("user", user);
        model.put("account", account);
        return "accounts";
    }

    @PostMapping("/{accountId}")
    public String updateAccountName(@PathVariable Long userId, @PathVariable Long accountId, @RequestParam String accountName) {
        accountService.updateAccountName(accountId, accountName);
        return "redirect:/users/" + userId + "/accounts/" + accountId;
    }


}
