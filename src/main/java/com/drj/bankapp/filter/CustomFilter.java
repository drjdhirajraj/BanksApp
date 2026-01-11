package com.drj.bankapp.filter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class CustomFilter {


    @Bean
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {

        http.csrf(c -> c.disable()).httpBasic(Customizer.withDefaults()).authorizeHttpRequests(auth -> {
            auth.requestMatchers("/balance")
                    .hasAuthority("VIEW_BALANCE")
                    .requestMatchers("/loans").hasAuthority("VIEW_LOANS")
                    .requestMatchers("/cards").authenticated()
                    .requestMatchers("/viewBanalce")
                    .hasAnyAuthority("VIEW_BALANCE", "VIEW_LOANS", "VIEW_CARDS")
                    .requestMatchers("/login", "/register","/getAllData").permitAll()
                    .anyRequest().authenticated();
        });

        return http.build();
    }


}
