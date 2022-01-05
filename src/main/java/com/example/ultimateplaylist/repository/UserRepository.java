package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // to regisiter
    boolean existsByEmailAddress(String userEmailAddress);

    // to login
    User findUserByEmailAddress(String userEmailAddress);
}