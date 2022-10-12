import configuration.ProjectConfiguration;
import musicstore.Album;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Album album = context.getBean(Album.class);
        System.out.println(album.getArtist().getName());
    }

}
