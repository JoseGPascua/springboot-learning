package com.example.demo.security;

import com.example.demo.mappings.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User
                .withUsername("admin")
                .authorities("BASIC", "SPECIAL")
                .password(encoder.encode("1")) //spring boot wont allow us to use raw test for passwords
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("2"))
                .authorities("BASIC")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)  // allows for POST, PUT, DELETE mappings with authentication
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/open").permitAll();
                    authorize.requestMatchers("/closed").authenticated();
                    authorize.requestMatchers(HttpMethod.POST, "/product").authenticated();

                    authorize.requestMatchers(HttpMethod.GET, "/special").hasAuthority("SPECIAL");
                    authorize.requestMatchers(HttpMethod.GET, "/basic").hasAnyAuthority("SPECIAL", "BASIC");
                })
                .httpBasic(Customizer.withDefaults())   // for basic authorization
                .build();
    }
}
