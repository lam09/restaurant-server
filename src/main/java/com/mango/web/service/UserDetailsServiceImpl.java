package com.mango.web.service;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */


import com.mango.web.entity.Account;
import com.mango.web.entity.Role;
import com.mango.web.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.print("user name is "+username);
        Account user = accountRepository.findAccountByUsername(username);
       // System.out.println("Account= " + user.getName());

        if (user == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }

        // EMPLOYEE,MANAGER,..
        if(user.getRoles()==null) System.out.print("no role");
        Set<Role> roles = user.getRoles();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        for(Role role: roles)
        {
            // ROLE_EMPLOYEE, ROLE_MANAGER
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            grantList.add(authority);
        }
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(user.getUsername(), //
                user.getEncrytedPassword(), true, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);

        return userDetails;
    }

/*

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }

*/
}