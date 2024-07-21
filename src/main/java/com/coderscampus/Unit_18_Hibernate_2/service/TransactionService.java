package com.coderscampus.Unit_18_Hibernate_2.service;

import com.coderscampus.Unit_18_Hibernate_2.repo.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

}
