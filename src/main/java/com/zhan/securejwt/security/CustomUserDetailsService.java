package com.zhan.securejwt.security;

import com.zhan.securejwt.model.User;
import com.zhan.securejwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user =  userRepository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));

       return UserPrincipal.create(user);
    }


    public UserDetails loadById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not foubnd"));

        return UserPrincipal.create(user);
    }



}
