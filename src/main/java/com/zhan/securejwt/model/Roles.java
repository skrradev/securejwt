package com.zhan.securejwt.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName roleName;

}
