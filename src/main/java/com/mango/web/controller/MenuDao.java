package com.mango.web.controller;

import com.mango.web.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by a.lam.tuan on 19. 7. 2018.
 */
@Repository
public interface MenuDao extends JpaRepository<Menu,Integer> {

}
