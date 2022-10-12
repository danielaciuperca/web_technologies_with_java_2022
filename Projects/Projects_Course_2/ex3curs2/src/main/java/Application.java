import configuration.ProjectConfiguration;
import musicstore.Album;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Album album1 = context.getBean("album1", Album.class);
        System.out.println(album1.getArtist().getName());

        Album album2 = context.getBean("album2", Album.class);
        System.out.println(album2.getArtist().getName());
    }

}
