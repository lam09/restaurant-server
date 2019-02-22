package com.mango.web.controller;

import com.mango.web.entity.Account;
import com.mango.web.entity.Privilege;
import com.mango.web.entity.Restaurant;
import com.mango.web.forms.RestaurantRegisterForm;
import com.mango.web.repo.AccountRepository;
import com.mango.web.repo.PrivilegeRepository;
import com.mango.web.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@Controller
public class RestaurantController {


    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;
    @PostMapping("restaurant/register")
    @ResponseBody
    public ResponseEntity register(@RequestBody RestaurantRegisterForm form, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Account acc = accountRepository.findAccountByUsername(username).get();
        if(acc == null) throw new BadCredentialsException("user not found");
        Restaurant restaurant = new Restaurant();
        restaurant.setAdmin(acc);
        restaurant.setName(form.getName());
        restaurant.setAddress(form.getAddress());
        restaurant.setTelefon(form.getTelefon());
        restaurant= restaurantRepository.insert(restaurant);
        Privilege privilege = new Privilege();
        privilege.setAccount(acc);
        privilege.setRestaurant(restaurant);
        privilege.setRoles(Arrays.asList("ROLE_RES_MANAGER","ROLE_RES_WAITER","ROLE_RES_MEMBER"));
        privilegeRepository.insert(privilege);
        Map<Object, Object> model = new HashMap<>();
        model.put("restaurant",restaurant);
        model.put("admin",acc);
        return ok(model);
    }


}
