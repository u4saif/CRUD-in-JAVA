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

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //Define Query for user with username where table NAME emp-info
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, pwd, active from usersInfo where username=?");

        //Define query for authorities with username
        jdbcUserDetailsManager.setGroupAuthoritiesByUsernameQuery(
                "select username , authority from authorities where username=?"
        );

        return jdbcUserDetailsManager;

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

//****DEFAULT user and authorities used by JDBC
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

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
//************************************************************
//***************Sql scripts for Database*********************
//************************************************************
//    DROP TABLE IF EXISTS `usersInfo`;
//
//---- Table structure for table `usersInfo`
//
//
//    CREATE TABLE `usersInfo` (
//            `username` varchar(50) NOT NULL,
//  `pwd` varchar(68) NOT NULL,
//  `active` tinyint NOT NULL,
//    PRIMARY KEY (`username`)
//) ENGINE=InnoDB DEFAULT CHARSET=latin1;
//
//--
//        -- Inserting data for table `users`
//            --
//
//    INSERT INTO `users`
//    VALUES
//            ('john','{noop}test123',1),
//('saif','{noop}test123',1);
//
//
//--
//        -- Table structure for table `authorities`
//            --
//
//    CREATE TABLE `authorities` (
//            `username` varchar(50) NOT NULL,
//  `authority` varchar(50) NOT NULL,
//    UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
//    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
//            ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
//
//--
//        -- Inserting data for table `authorities`
//            --
//
//    INSERT INTO `authorities`
//    VALUES
//            ('john','ROLE_EMPLOYEE'),
//('saif','ROLE_ADMIN'),
//        ('saif','ROLE_MANAGER');
//---------------------for Storing Encrypted password--------------------
//    {bcrypt}$2a$10$7LZMnOLTLcpL/ZH8rNlRz.7cEm.gz3o67Ul2h7rYhGgTf1r9EJjIG

}
