package musicstore;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component  //step 1 - stereotype annotation
public class Album {
    private String artist;
    private LocalDate releaseDate;
    private double price;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
