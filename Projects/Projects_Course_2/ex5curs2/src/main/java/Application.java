import configuration.ProjectConfiguration;
import musicstore.Album;
import musicstore.LimitedEditionCollection;
import musicstore.Single;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Album album = context.getBean(Album.class);
        System.out.println(album.getArtist().getName());

        Single single = context.getBean(Single.class);
        System.out.println(single.getArtist().getName());

        LimitedEditionCollection limitedEditionCollection = context.getBean(LimitedEditionCollection.class);
        System.out.println(limitedEditionCollection.getArtist().getName());
    }
}
