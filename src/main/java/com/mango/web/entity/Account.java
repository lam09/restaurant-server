package com.mango.web.entity;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT",
        uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class Account implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    @Column(name = "ENCRYTED_PASSWORD", length = 128, nullable = false)
    private String encrytedPassword;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ACCOUNT_ROLE",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}