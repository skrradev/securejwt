package com.zhan.securejwt.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@Data
public class MyGrantedAuthorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private  String role;

    public MyGrantedAuthorities(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    public MyGrantedAuthorities() {
    }


    @Override
    public String getAuthority() {
        return role;
    }
}
