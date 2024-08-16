package com.coderscampus.Unit_18_Hibernate_2.repo;

import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
//                                                 <Entity, Data Type of ID>
public interface UserRepository extends JpaRepository<User, Long> {

    // select * from users where username = :username
    //    handle same username users in the service
    List<User> findByUsername(String username);

    // select * from users where name = :name
    //    handle same name users in the service
    List<User> findByName(String name);

    // select * from users where username = :username and name = :name
    List<User> findByUsernameAndName(String username, String name);

    // select * users between date1
    List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2);


    @Query("SELECT u FROM User u LEFT JOIN FETCH u.address WHERE u.userId = :userId")
    Optional<User> findByIdWithAddress(@Param("userId") Long userId);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.accounts")
    List<User> findAllWithAccounts();
}
