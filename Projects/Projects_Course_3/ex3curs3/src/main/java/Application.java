import configuration.ProjectConfiguration;
import musicstore.Store;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        System.out.println("The Spring context is ready");

        Store store1 = context.getBean(Store.class);
        store1.setName("My store");
        System.out.println("store name: " + store1.getName());

        Store store2 = context.getBean(Store.class);
        System.out.println("store2 name: " + store2.getName());
    }
}
