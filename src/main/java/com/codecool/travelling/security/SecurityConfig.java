package com.codecool.travelling.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
This class configures Spring Security.
By default Spring Security locks down all your endpoints and one must use HTTP Basic authentication to access anything,
we need to override this with our configuration.
*/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    // By overriding this function and
    // adding the @Bean annotation we can inject the AuthenticationManager into other classes.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // The main point for configuring Spring Security.
    // In Spring Security every request goes trough a chain of filters; each filter checks the request for something
    // and if one fails the request will fail.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // By default Spring Security uses HTTP Basic authentication, we disable this filter.
                .csrf().disable() // Disable CSRF. Leaving it enabled would ignore GET, HEAD, TRACE, OPTIONS
                // Disable Tomcat's session management. This causes HttpSession to be null and no session cookie to be created
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // restrict access based on the config below:
                .antMatchers("/v2/**").permitAll() // for testing
                .antMatchers("/swagger-ui.html/**").permitAll() // for testing
                .antMatchers("/swagger-resources/**").permitAll() // for testing
                .antMatchers("/webjars/**").permitAll() // for testing
                .antMatchers("/auth/**").permitAll() // allowed by anyone
                .antMatchers("/registration/**").permitAll() // allowed by anyone
                .antMatchers("/position/**").permitAll() // allowed by anyone
                //.antMatchers("/position/add-position").hasRole("COMPANY") // allowed if signed in with COMPANY role
                .antMatchers("/position/add-position").permitAll() // allowed if signed in with COMPANY role
                //.antMatchers("/position/search/**").hasRole("SALESMAN") // allowed if signed in with COMPANY role
                .antMatchers("/position/search/**").permitAll() // allowed if signed in with COMPANY role
                .antMatchers("/salesman/**").permitAll() //allowed by anyone
                //.antMatchers("/personality/match-person-to-role/**").hasRole("SALESMAN") //allowed if signed in as SALESMAN
                .antMatchers("/personality/match-person-to-role/**").permitAll() //allowed if signed in as SALESMAN
                //.antMatchers("/personality/get-all-matching-position/**").hasRole("SALESMAN") //allowed if signed in as SALESMAN
                .antMatchers("/personality/get-all-matching-position/**").permitAll() //allowed if signed in as SALESMAN
                .antMatchers("/personality/add-personality-to-salesman").permitAll()
                //.antMatchers("/personality/add-personality-to-salesman").hasRole("SALESMAN)
                .anyRequest().denyAll() // anything else is denied; this is a safeguard in case we left something out.
                .and()
                // Here we define our custom filter that uses the JWT tokens for authentication.
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }
}


