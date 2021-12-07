package com.hot.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

    @Autowired
    private DataSource dataSource;

    // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            //.usersByUsernameQuery("select username, password from users where username=?")
            //.authoritiesByUsernameQuery("select username, authority from authorities where username=?")
            .passwordEncoder(new BCryptPasswordEncoder());

  
        /*
        //auth.inMemoryAuthentication()
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //.passwordEncoder(passwordEncoder())
                .withUser("u1")
                //.password(passwordEncoder().encode("p1"))
                .password("p1")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}password")
                .roles("USER", "ADMIN");
    */
    }
    
    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

  

}
