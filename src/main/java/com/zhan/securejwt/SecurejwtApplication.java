package com.zhan.securejwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurejwtApplication {



    public static void main(String[] args) {
        SpringApplication.run(SecurejwtApplication.class, args);

        System.out.println(new BCryptPasswordEncoder().encode("test"));

    }

}
