/*package com.example.user_profile_post_api.security.Session_based_Authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity //Optional
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder); //passwordEncoder.matches("e1234", "$2a$10$3H5mC9...") --- returns true or false
        return daoAuthenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,AuthenticationProvider authProvider) throws Exception
    {
        return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider).build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,AuthenticationProvider provider) throws Exception
    {
        http.csrf(csrf->csrf.disable())

                .authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**")
                        .permitAll().anyRequest().authenticated())


                .formLogin(login -> login
                        .loginProcessingUrl("/api/auth/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                        .successHandler((req, res, auth) -> {
                            res.setStatus(200);
                            res.getWriter().write("Login success");
                        })
                        .failureHandler((req, res, ex) -> {
                            System.out.println("Login failed: " + ex.getMessage());
                            res.setStatus(401);
                            res.getWriter().write("Invalid username or password");
                        })
                )

 */

/*
.formLogin() → internally UsernamePasswordAuthenticationFilter call হয় → যা AuthenticationManager-কে invoke(dake) করে।

AuthenticationManager → DaoAuthenticationProvider

DaoAuthenticationProvider → UserDetailsService call করে DB থেকে user load করে

Password match হয় → OK → authentication success
 */

/*


                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")       // logout URL
                        .deleteCookies("JSESSIONID")         // browser থেকে cookie remove করবে
                        .invalidateHttpSession(true)         // session invalidate করবে
                        .clearAuthentication(true)           // SecurityContext clear করবে
                        .logoutSuccessHandler((req, res, auth) -> {
                            res.setStatus(200);              // HTTP 200 OK
                            res.setContentType("application/json");
                            res.getWriter().write("{\"message\":\"Logout success\"}"); // JSON response
                        })
                );


        return http.build();
    }


}


*/