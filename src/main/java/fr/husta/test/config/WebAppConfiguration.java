package fr.husta.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;

@Configuration
@EnableMBeanExport
@Import({WebSecurityConfiguration.class})
public class WebAppConfiguration {

}
