package com.mango.web.service;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */


import com.google.gson.Gson;
import com.mango.web.entity.Account;
import com.mango.web.entity.Privilege;
import com.mango.web.entity.Restaurant;
import com.mango.web.repo.AccountRepository;
import com.mango.web.repo.PrivilegeRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }


    public UserDetails loadUserPrivilegesWithRestaurant(String username, Restaurant restaurant) throws UsernameNotFoundException {
        if(restaurant==null) return loadUserByUsername(username);
        Account account = accountRepository.findAccountByUsername(username).get();
        if(account==null)System.out.println("account not found");
        List<String> roles = account.getRoles();
        account.getPrivileges().stream()
                .filter(privilege -> privilege.getRestaurant().getId().compareTo(restaurant.getId())==0)
                .forEach(privilege -> {roles.addAll(privilege.getRoles());});
        account.setRoles(roles);
        return account;
    }

  /*  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.print("user email is "+username);
        Account user = accountRepository.findAccountByUsername(username).get();

        if (user == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }
     List<GrantedAuthority> grantList = user.getAuthorities().stream().collect(Collectors.toList());
         boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(user.getUsername(), //
                user.getPassword(), true, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);

        return userDetails;
    }
*/
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