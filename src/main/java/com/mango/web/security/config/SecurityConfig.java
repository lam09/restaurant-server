package com.mango.web.security.config;

import com.mango.web.security.jwt.JwtConfigurer;
import com.mango.web.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/restaurants").authenticated()
                .antMatchers(HttpMethod.POST, "/restaurant/register").authenticated()
                .antMatchers(HttpMethod.PUT,"/restaurant/update").hasRole("RES_MANAGER")
                .antMatchers(HttpMethod.POST, "/food/new").hasRole("RES_WAITER")
                .antMatchers(HttpMethod.GET, "/food/get").hasRole("RES_WAITER")
                .antMatchers(HttpMethod.DELETE, "/food/delete").hasRole("RES_WAITER")
                .antMatchers(HttpMethod.POST, "/food/update").hasRole("RES_WAITER")
                .antMatchers(HttpMethod.GET, "/food/all").hasRole("RES_WAITER")
                .antMatchers("/food/get").authenticated()
                .antMatchers("/order/new").hasRole("RES_WAITER")
                .antMatchers("/order/get").hasRole("RES_WAITER")
                .antMatchers("/order/update").hasRole("RES_WAITER")
                .antMatchers("/order/delete").hasRole("RES_WAITER")

                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }
}