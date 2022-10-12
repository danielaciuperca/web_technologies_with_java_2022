import configuration.ProjectConfiguration;
import musicstore.Album;
import musicstore.Guitar;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = //step 3 - make Spring use that @Configuration class
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Album album = context.getBean(Album.class); //getting a reference of a bean of type Album
        System.out.println(album.getArtist());
    }

}
