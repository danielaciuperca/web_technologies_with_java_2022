package configuration;

import musicstore.Album;
import musicstore.Artist;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;

@Configuration
public class ProjectConfiguration {

    @Bean
    public Artist artist1(){
        Artist artist = new Artist();
        artist.setName("Debussy");
        return artist;
    }

    @Bean
    public Artist artist2(){
        Artist artist = new Artist();
        artist.setName("Chopin");
        return artist;
    }

    @Bean
    public Album album(@Qualifier(value = "artist2") Artist artist) {
        Album album = new Album(artist, LocalDate.now(), 100.50);
        return album;
    }
}
