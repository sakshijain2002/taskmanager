package com.task.taskmanager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure authentication (e.g., in-memory users, database authentication)
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}pass") // {noop} for plain text password (not recommended in production)
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disabling CSRF for simplicity
                .authorizeRequests()
                .antMatchers("/api/tasks/**").authenticated() // Secure these endpoints
                .anyRequest().permitAll() // Allow all other requests without authentication
                .and()
                .httpBasic(); // Use basic authentication (can use other methods like form login, JWT, etc.)
    }
}
