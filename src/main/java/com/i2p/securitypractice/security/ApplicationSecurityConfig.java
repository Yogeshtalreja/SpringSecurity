package com.i2p.securitypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index" , "/css/*" , "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
       UserDetails yogeshUser =  User.builder()
                .username("Yogesh")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.STUDENT.name()) //ROLE_STUDENT
               .build();

     UserDetails soorajUser =   User.builder()
               .username("Sooraj")
               .password(passwordEncoder.encode("password123"))
               .roles(ApplicationUserRole.ADMIN.name())
               .build();

        UserDetails fahadUser =   User.builder()
                .username("Fahad")
                .password(passwordEncoder.encode("password1234"))
                .roles(ApplicationUserRole.ADMINTrainee.name())  //ROle ADMIN_TRAINEE
                .build();


        return new InMemoryUserDetailsManager(
               yogeshUser ,
               soorajUser,
                fahadUser
       );
    }
}
