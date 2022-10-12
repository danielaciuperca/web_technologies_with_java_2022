package musicstore;

import java.time.*;

public class Album {
    private Artist artist;
    private LocalDate releaseDate;
    private double price;

    public Album(Artist artist, LocalDate releaseDate, double price) {
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
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
