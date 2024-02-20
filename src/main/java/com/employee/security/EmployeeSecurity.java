package com.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class EmployeeSecurity {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET,"/employee/list/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST,"/employee/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/employee/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/employee/**").hasRole("ADMIN")

        );

        //HTTP basic authorization
        http.httpBasic(Customizer.withDefaults());

        //Disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

//*********In Memory User creation
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails saif = User.builder()
//                .username("saif")
//                .password("{noop}ey123")
//                .roles("ADMIN","EMPLOYEE")
//                .build();
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}ey123")
//                .roles("EMPLOYEE")
//                .build();
//
//        return new InMemoryUserDetailsManager(saif,john);
//    }
}
