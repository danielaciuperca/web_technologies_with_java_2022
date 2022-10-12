package musicstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {
    private Artist artist;
    private double price;

    @Autowired //2. DI through the constructor
    public Single(Artist artist) { //2. DI through constructor - injecting the value through the constructor parameter
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
