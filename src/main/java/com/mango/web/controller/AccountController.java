package com.mango.web.controller;

import com.mango.web.entity.Account;
import com.mango.web.forms.AccountRegisterFrom;
import com.mango.web.forms.AuthenticationRequest;
import com.mango.web.repo.AccountRepository;
import com.mango.web.repo.PrivilegeRepository;
import com.mango.web.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AccountController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AccountRepository users;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping(value = "/auth/login",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        if(data==null){
            return badRequest().build();
        }
        System.out.println("user is authenticating " );
            try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            System.out.println("user is authenticated " );
            Account acc = users.findAccountByUsername(username).get();
            String token = jwtTokenProvider.createToken(username, this.users.findAccountByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            // ArrayList<String> privilegeIds  = new ArrayList<>();
                //  privilegeRepository.findAllByAccount(acc).stream().forEach(s->privilegeIds.add(s.getId()));
             //   String tokenFromPrivileges = jwtTokenProvider.createToken(username,acc.getRoles(), privilegeIds);
            System.out.println("token generated " + token);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
//                System.out.println("error " );
//                throw new BadCredentialsException("Invalid username/password supplied");
                return badRequest().build();
        }
    }

    @PostMapping(value = "/auth/register",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity register(@RequestBody AccountRegisterFrom data) {
        if(users.findAccountByUsername(data.getUsername()).isPresent()) return badRequest().build();
        if(users.findAccountByEmail(data.getEmail()).isPresent()) return badRequest().build();
        Account acc = new Account();
        acc.setUsername(data.getUsername());
        acc.setEmail(data.getEmail());
        acc.setPassword(passwordEncoder.encode(data.getPassword()));
        acc = users.save(acc);
        Map<Object, Object> model = new HashMap<>();
        model.put("userId", acc.getId());
        return ok(model);
    }


    @GetMapping("/auth/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        return ok(model);
    }
    @GetMapping("/author/restaurant")
    public ResponseEntity authorizateCurrentUser(@AuthenticationPrincipal Account userDetails){

        return ok(null);
    }

}
