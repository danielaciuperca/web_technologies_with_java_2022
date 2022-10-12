package configuration;

import musicstore.Album;
import musicstore.Guitar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //step 1 - define a @Configuration class
public class ProjectConfiguration {

    @Bean   //step 2 - add a method returning an instance and annotate it with @Bean
    public Album album() {  //method name = unique identifier of the bean
        Album album = new Album();
        album.setArtist("Queen");
        return album;   //creates an instance and puts it in the Spring context
    }

    @Bean
    public String greeting() {
        return "Hello";
    }

    //same type (Guitar), but 2 different method names, results in 2 different beans put in the context
    @Bean
    public Guitar classicalGuitar() {
        Guitar guitar = new Guitar();
        guitar.setBrand("Yamaha");
        return guitar;
    }

    @Bean//(name = "myElectricGuitar")
    public Guitar electricGuitar() {
        Guitar guitar = new Guitar();
        guitar.setBrand("Fender");
        return guitar;
    }
}
