package com.zhan.securejwt.model;


import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "\"user\"")
@Data
public class User {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String username;

    private String password;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;


}
