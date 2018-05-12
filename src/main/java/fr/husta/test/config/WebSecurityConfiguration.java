package fr.husta.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static fr.husta.test.security.WebSecurityConstants.Roles.ADMIN;
import static fr.husta.test.security.WebSecurityConstants.Roles.USER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user").password("password").roles(USER)
                .build();
        manager.createUser(userDetails);

        UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin*").roles(ADMIN)
                .build();
        manager.createUser(userAdmin);
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(ADMIN)
                .antMatchers("/secured/**").hasRole(USER)
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic()
                .and()
                .headers();
    }
}
