package com.mango.web.repo;

import com.mango.web.entity.Account;
import com.mango.web.entity.Privilege;
import com.mango.web.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PrivilegeRepository  extends MongoRepository<Privilege,String>,PrivilegeCustomRepository {
    List<Privilege> findAllByAccount(Account account);
    List<Privilege> findAllByRestaurant(Restaurant restaurant);
    Optional<Privilege> findPrivilegeByAccountAndRestaurant(Account account,Restaurant restaurant);
}
