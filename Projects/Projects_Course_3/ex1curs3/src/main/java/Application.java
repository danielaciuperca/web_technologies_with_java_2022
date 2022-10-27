import configuration.ProjectConfiguration;
import model.musicstore.Album;
import model.musicstore.Artist;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AlbumService;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        AlbumService albumService = context.getBean(AlbumService.class);
        Artist artist = new Artist("Elton John", "UK");
        Album album = new Album(artist, LocalDate.of(1995, 10, 6), 260.5);

        albumService.create(album);
        albumService.delete(album);
    }
}
