package com.zhan.securejwt.payload;


import lombok.Data;

@Data
public class SignUpRequest {


    private Long id;

    private String name;

    private String username;

    private String password;

    private String email;


}
