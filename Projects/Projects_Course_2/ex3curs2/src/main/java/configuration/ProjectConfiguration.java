package configuration;

import musicstore.Album;
import musicstore.Artist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {

    @Bean
    public Artist artist(){
        Artist artist = new Artist();
        artist.setName("Debussy");
        return artist;
    }

    @Bean
    public Album album1() {
        Album album = new Album();
        album.setArtist(artist());  //wiring using a direct method call between the @Bean methods
        return album;
    }

    @Bean
    public Album album2() {
        Album album = new Album();
        album.setArtist(artist());  //wiring using a direct method call between the @Bean methods
        return album;
    }
}
