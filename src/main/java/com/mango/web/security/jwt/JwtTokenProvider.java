package com.mango.web.security.jwt;

import com.mango.web.entity.Account;
import com.mango.web.entity.Privilege;
import com.mango.web.entity.Restaurant;
import com.mango.web.repo.AccountRepository;
import com.mango.web.repo.PrivilegeRepository;
import com.mango.web.repo.RestaurantRepository;
import com.mango.web.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000*24*30; // 1month

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

 /*   public String createTokenFromPrivileges(String username,List<String>roles, List<String> privileges) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles",roles);
        claims.put("privileges",privileges);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }
*/

    public Authentication getAuthentication(String token,String res_id) {
        String username = getUsername(token);
        Restaurant restaurant=null;
        if(res_id!=null)restaurant= restaurantRepository.findRestaurantById(res_id);
        UserDetails userDetails = null;
        if(restaurant==null)
            userDetails = this.userDetailsService.loadUserByUsername(username);
        else userDetails = this.userDetailsService.loadUserPrivilegesWithRestaurant(username,restaurant);
        System.out.println("User authorities "+userDetails.getAuthorities().toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        return token;
   }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
//            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

}