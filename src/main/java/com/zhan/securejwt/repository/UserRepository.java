package com.zhan.securejwt.repository;

import com.zhan.securejwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findUserByUsernameOrEmail(String username, String email);

    Optional<User> findUserByUsername(String username);
}
