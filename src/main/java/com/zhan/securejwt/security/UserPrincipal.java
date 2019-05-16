package com.zhan.securejwt.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhan.securejwt.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UserPrincipal implements UserDetails {



    private Long id;

    private String username;

    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public static UserDetails create(User user) {


        return new UserPrincipal(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
