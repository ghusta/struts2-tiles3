package fr.husta.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"fr.husta.test.service"})
public class AppConfiguration {

    @Autowired
    private Environment env;

}
