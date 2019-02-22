package com.mango.web.security.jwt;

import com.mango.web.entity.Restaurant;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenRestaurantPrivilegeFilter extends GenericFilterBean {


    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenRestaurantPrivilegeFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // String restaurantId = (HttpServletRequest) servletRequest.
    }
}
