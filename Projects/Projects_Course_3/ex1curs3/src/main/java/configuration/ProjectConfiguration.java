package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"service", "aspect"})
@EnableAspectJAutoProxy     //step 1 - enable the AspectJ mechanism
public class ProjectConfiguration {

}
