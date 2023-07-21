package fr.husta.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static fr.husta.test.security.WebSecurityConstants.Roles.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Note that the matchers are considered in order.
                // Doc : https://docs.spring.io/spring-security/site/docs/current/reference/html5/index.html#jc-authorize-requests
                .authorizeRequests(auth -> auth
                        .antMatchers("/admin/superadmin/**").hasRole(SUPERADMIN)
                        .antMatchers("/admin/**").hasRole(ADMIN)
                        .antMatchers("/secured/**").hasAnyRole(USER, SUPERUSER)
                        // '/mixed/**' : To be refined in actions with annotations
                        .antMatchers("/mixed/**").hasAnyRole(USER, SUPERUSER)
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .anonymous(ano -> ano.disable())
                .servletApi(withDefaults())
                .csrf(withDefaults())
                .headers(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user").password("password").roles(USER)
                .build();

        UserDetails superuserDetails = User.withDefaultPasswordEncoder()
                .username("superuser").password("password").roles(SUPERUSER)
                .build();

        UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin*").roles(ADMIN)
                .build();

        UserDetails god = User.withDefaultPasswordEncoder()
                .username("god").password("jesus").roles(USER, ADMIN, SUPERADMIN)
                .build();

        return new InMemoryUserDetailsManager(userDetails, superuserDetails, userAdmin, god);
    }

}
