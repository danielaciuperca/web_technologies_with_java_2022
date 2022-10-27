import configuration.*;
import musicstore.*;
import org.springframework.context.annotation.*;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Album album1 = context.getBean(Album.class);
        album1.setArtist("Metallica");
        System.out.println("album1 has artist: " + album1.getArtist());
        System.out.println("album1 hashCode: " + album1);

        Album album2 = context.getBean(Album.class);
        System.out.println("album2 has artist: " + album2.getArtist());
        System.out.println("album2 hashCode: " + album2);

        Guitar guitar1 = context.getBean(Guitar.class);
        guitar1.setBrand("Yamaha");
        System.out.println("guitar1 has brand: " + guitar1.getBrand());
        System.out.println("guitar1 hashCode: " + guitar1);

        Guitar guitar2 = context.getBean(Guitar.class);
        System.out.println("guitar2 has brand: " + guitar2.getBrand());
        System.out.println("guitar2 hashCode: " + guitar2);

    }
}
