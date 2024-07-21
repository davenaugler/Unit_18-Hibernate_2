package com.coderscampus.Unit_18_Hibernate_2.repo;

import com.coderscampus.Unit_18_Hibernate_2.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
