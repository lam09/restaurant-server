package com.mango.web.config;

import com.mango.web.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountRepository accountRepository;



    //@Autowired
    //private PasswordEncoder passwordEncoder;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
/*
        PaymentMethod paymentMethod1=new PaymentMethod();
        paymentMethod1.setName("ON DELIVERY");
        paymentMethod1.setFee(1.00);
        PaymentMethod paymentMethod2=new PaymentMethod();
        paymentMethod2.setName("BANK TRANSFER");
        paymentMethod2.setFee(0.00);
        PaymentMethod paymentMethod3=new PaymentMethod();
        paymentMethod3.setName("CARD");
        paymentMethod3.setFee(1.00);

        paymentMethodRepository.save(paymentMethod1);
        paymentMethodRepository.save(paymentMethod2);
        paymentMethodRepository.save(paymentMethod3);

        ShippingMethod shippingMethod1=new ShippingMethod();
        shippingMethod1.setName("POSTA");
        shippingMethod1.setFee(2.00);
        ShippingMethod shippingMethod2=new ShippingMethod();
        shippingMethod2.setName("OSOBNE");
        shippingMethod2.setFee(0.00);
        ShippingMethod shippingMethod3=new ShippingMethod();
        shippingMethod3.setName("DHL");
        shippingMethod3.setFee(2.00);

        shippingMethodRepository.save(shippingMethod1);
        shippingMethodRepository.save(shippingMethod2);
        shippingMethodRepository.save(shippingMethod3);
*/

/*
        Role member = addRole("MEMBER");
        Role admin = addRole("ADMIN");

        Set<Role> set=new HashSet<>();
        set.add(admin);
        addAccount("admin", "123456",set );
        set.clear();
        set.add(member);
        addAccount("user","123456",set);*/
    }

  /*  private Account addAccount(String name, String password, Set<Role> roles){
        Account user = new Account();
        user.setUsername(name);
        user.setEncrytedPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        return accountRepository.save(user);
    }

    private Role addRole(String name)
    {
        Role role =roleRepository.findRoleByName(name);
        if (role == null) {
            return roleRepository.save(new Role(name));
        }
        else return role;
    }
*/
}
