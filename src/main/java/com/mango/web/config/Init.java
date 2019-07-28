package com.mango.web.config;

import com.mango.web.entity.Account;
import com.mango.web.repo.AccountRepository;
import com.mango.web.repo.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class Init implements CommandLineRunner {

    @Autowired
    AccountRepository users;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        //...
   /*     Account member = new Account();
        member.setEmail("member@hello.com");

        member.setUsername("user");
        member.setPassword(this.passwordEncoder.encode("123456"));
        member.setRoles(Arrays.asList( "ROLE_USER"));
        this.users.save(member);
        Account admin = new Account();
        admin.setEmail("admin@hello.com");
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("123456"));
        admin.setRoles(Arrays.asList( "ROLE_USER,ROLE_ADMIN"));
        this.users.save(admin);*/
       // System.out.println("restaurant find by id "+restaurantRepository.findRestaurantById("5c716922d9489e439065adf7").getName());
      //  this.restaurantRepository.findAll().forEach(r->System.out.println("restaurant " + r.getId() + " "+ r.getName()));
       // this.users.findAll().forEach(v -> System.out.println(" User :" + v.toString()));

    }
}