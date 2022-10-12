package musicstore;

public class Artist {
    private String name;
    private String country;

    public Artist() {
        System.out.println("s-a apelat constructorul de Artist");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
