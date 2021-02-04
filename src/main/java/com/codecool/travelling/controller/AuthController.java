package com.codecool.travelling.controller;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.model.UserCredentials;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import com.codecool.travelling.security.JwtTokenServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private SalesmanRepository salesmanRepository;
    private CompanyRepository companyRepository;


    public AuthController(SalesmanRepository salesmanRepository, AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, CompanyRepository companyRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.salesmanRepository = salesmanRepository;
        this.companyRepository =companyRepository;
    }

    @PostMapping("/salesman-login")
    public ResponseEntity salesmanLogin(@RequestBody UserCredentials data) {
        Optional<Salesman> user = salesmanRepository.findByUsername(data.getUsername());
        UUID id = user.get().getId();
        try {
            String username = data.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("userId", id);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/company-login")
    public ResponseEntity companyLogin(@RequestBody UserCredentials data) {
        Optional<Company> user = companyRepository.findByUsername(data.getUsername());
        UUID id = user.get().getId();
        try {
            String username = data.getUsername();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("userId", id);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
