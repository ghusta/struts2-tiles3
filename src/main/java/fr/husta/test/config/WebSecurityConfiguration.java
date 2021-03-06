package fr.husta.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static fr.husta.test.security.WebSecurityConstants.Roles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user").password("password").roles(USER)
                .build();
        manager.createUser(userDetails);

        UserDetails superuserDetails = User.withDefaultPasswordEncoder()
                .username("superuser").password("password").roles(SUPERUSER)
                .build();
        manager.createUser(superuserDetails);

        UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin*").roles(ADMIN)
                .build();
        manager.createUser(userAdmin);

        UserDetails god = User.withDefaultPasswordEncoder()
                .username("god").password("jesus").roles(USER, ADMIN, SUPERADMIN)
                .build();
        manager.createUser(god);

        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Note that the matchers are considered in order.
                // Doc : https://docs.spring.io/spring-security/site/docs/current/reference/html5/index.html#jc-authorize-requests
                .antMatchers("/admin/superadmin/**").hasRole(SUPERADMIN)
                .antMatchers("/admin/**").hasRole(ADMIN)
                .antMatchers("/secured/**").hasAnyRole(USER, SUPERUSER)
                // '/mixed/**' : To be refined in actions with annotations
                .antMatchers("/mixed/**").hasAnyRole(USER, SUPERUSER)
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic()
                .and()
                .anonymous().disable()
                .servletApi()
                .and()
                .csrf()
                .and()
                .headers();
    }
}
