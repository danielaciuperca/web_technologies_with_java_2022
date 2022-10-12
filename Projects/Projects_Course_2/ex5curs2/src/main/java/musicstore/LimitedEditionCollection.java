package musicstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LimitedEditionCollection {
    private Artist artist;
    private double price;
    private int numberOfCopies;

    public Artist getArtist() {
        return artist;
    }

    @Autowired //3. DI through the setter
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}
