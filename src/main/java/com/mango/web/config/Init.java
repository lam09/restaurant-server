package com.mango.web.config;

import com.mango.web.entity.Account;
import com.mango.web.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class Init implements CommandLineRunner {

    @Autowired
    AccountRepository users;


    @Override
    public void run(String... args) throws Exception {
        //...
    /*    Account member = new Account();
        member.setEmail("member@hello.com");

        member.setUsername("user");
        member.setPassword(this.passwordEncoder().encode("password"));
        member.setRoles(Arrays.asList( "ROLE_USER"));
        this.users.save(member);
        Account admin = new Account();
        admin.setEmail("admin@hello.com");
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder().encode("password"));
        admin.setRoles(Arrays.asList( "ROLE_USER,ROLE_ADMIN"));
        this.users.save(admin);*/
        this.users.findAll().forEach(v -> System.out.println(" User :" + v.toString()));

    }
}