package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "musicstore") //step 2 - @ComponentScan on a @Configuration class
public class ProjectConfiguration {

}
