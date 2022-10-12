import configuration.ProjectConfiguration;
import musicstore.Album;
import musicstore.Guitar;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = //step 3 - make Spring use that @Configuration class
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

//        Album album1 = context.getBean(Album.class);  //by type
//        System.out.println("album1 artist: " + album1.getArtist());

//        Guitar guitar1 = context.getBean(Guitar.class);//by type
//        System.out.println(guitar1.getBrand());

        Guitar guitar1 = context.getBean("classicalGuitar", Guitar.class);//by name
        System.out.println("guitar1 brand: " + guitar1.getBrand());

        Guitar guitar2 = context.getBean("electricGuitar", Guitar.class);//by name
        System.out.println("guitar2 brand: " + guitar2.getBrand());
    }

}
