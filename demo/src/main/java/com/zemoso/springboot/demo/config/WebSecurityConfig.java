package com.zemoso.springboot.demo.config;

import com.zemoso.springboot.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/customerApi/showFormForRegistration").permitAll()
                .antMatchers("/customerApi/addCustomer").permitAll()
                .antMatchers("/customerApi/customers").access("hasAuthority('Admin')")
                .antMatchers("/carApi/cars").access("hasAuthority('Admin')")
                .antMatchers("/carApi/addCar").access("hasAuthority('Admin')")
                .antMatchers("/carApi/delete/**").access("hasAuthority('Admin')")
                .antMatchers("/customerApi/delete/**").access("hasAuthority('Admin')")
                .antMatchers("/carApi/showFormForUpdate/**").access("hasAuthority('Admin')")
                .antMatchers("/customerApi/showFormForUpdate/**").access("hasAuthority('Admin')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/carApi/carsView")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/customerApi/errorPage");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
