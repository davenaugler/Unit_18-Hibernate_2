package com.coderscampus.Unit_18_Hibernate_2.repo;

import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//                                                 <Entity, Data Type of ID>
public interface UserRepository extends JpaRepository<User, Long> {

}
