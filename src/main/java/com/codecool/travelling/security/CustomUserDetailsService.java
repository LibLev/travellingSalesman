package com.codecool.travelling.security;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private SalesmanRepository salesmans;
    private CompanyRepository companies;

    public CustomUserDetailsService(SalesmanRepository salesmans, CompanyRepository companies) {
        this.salesmans = salesmans;
        this.companies = companies;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (salesmans.findByUsername(username).isPresent()) {
            Salesman user = salesmans.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

            return new User(user.getUsername(), user.getPassword(),
                    user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }else{
            Company user = companies.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

            return new User(user.getUsername(), user.getPassword(),
                    user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        } }
}