package com.zhan.securejwt.repository;

import com.zhan.securejwt.model.MyGrantedAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<MyGrantedAuthorities, Long> {


    @Override
    Optional<MyGrantedAuthorities> findById(Long aLong);
}
