package com.victorp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .csrf()
                .disable()
                .authorizeRequests()
              //Access only for non-registered users
                .antMatchers("/registration").not().fullyAuthenticated()
              //Access only for users with the Administrator role
                .antMatchers("/administration/**").hasAnyRole("ADMIN","CLIENT")
                .antMatchers("/event").hasRole("CLIENT")
                .antMatchers("/trainerPage").hasAnyRole("ADMIN", "TRAINER")
              //Access is allowed to all users
                .antMatchers("/", "/static/**").permitAll().anyRequest().permitAll()
//              //All other pages require authentication  
//                .anyRequest().authenticated()
                .and()
              //Settings for login
                .formLogin()
                .loginPage("/login")
              //Redirect to the main page after successful login
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");


    }




    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}

