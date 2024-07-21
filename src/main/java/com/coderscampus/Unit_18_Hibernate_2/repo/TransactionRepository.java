package com.coderscampus.Unit_18_Hibernate_2.repo;

import com.coderscampus.Unit_18_Hibernate_2.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
