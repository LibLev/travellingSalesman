package com.codecool.travelling.controller;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Salesman;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private SalesmanRepository salesmanRepository;
    private CompanyRepository companyRepository;

    public AuthController(CompanyRepository companyRepository, SalesmanRepository salesmanRepository, AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.salesmanRepository = salesmanRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping("/salesman-login")
    public ResponseEntity salesmanLogin(@RequestBody Map<String, String> data) {
        Optional<Salesman> user = salesmanRepository.findByEmail(data.get("email"));
        UUID id = user.get().getId();
        try {
            String email = data.get("email");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.get("password")));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(email, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("userId", id);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/company-login")
    public ResponseEntity companyLogin(@RequestBody Map<String, String> data) {
        Optional<Company> user = companyRepository.findByEmail(data.get("email"));
        UUID id = user.get().getId();
        try {
            String email = data.get("email");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.get("password")));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(email, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("userId", id);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
