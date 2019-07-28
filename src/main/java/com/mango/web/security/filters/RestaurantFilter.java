package com.mango.web.security.filters;

import com.google.gson.Gson;
import com.mango.web.entity.Account;
import com.mango.web.entity.Restaurant;
import com.mango.web.repo.AccountRepository;
import com.mango.web.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantFilter extends GenericFilterBean {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Restaurant filter");
        String res_id = ((HttpServletRequest)req).getHeader("restaurant-id");
        if(res_id==null) {
            filterChain.doFilter(req,res);
            return;
        }
        System.out.println("Restaurant id"+res_id);
        Restaurant restaurant= restaurantRepository.findRestaurantById(res_id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        Account acc = accountRepository.findAccountByUsername(user.getUsername()).get();
        ArrayList<String> res_roles = new ArrayList<>();
        acc.getPrivileges().stream().filter((privilege -> privilege.getRestaurant()==restaurant)).forEach(p->{
            res_roles.addAll(p.getRoles());
        });
        List<GrantedAuthority> grantList = new ArrayList<>();
        res_roles.stream().forEach(r->{
            GrantedAuthority au = new SimpleGrantedAuthority(r);
            ((List<GrantedAuthority>)auth.getAuthorities()).add(au);
            //grantList.add(au);
        });
        SecurityContextHolder.getContext().setAuthentication(auth);
        System.out.println(new Gson().toJson(SecurityContextHolder.getContext().getAuthentication()));
       // auth.getAuthorities().addAll(grantList.stream().collect(Collectors.toList()));
//        grantList.addAll(auth.getAuthorities());

        filterChain.doFilter(req, res);
    }
}
