package service;

import model.musicstore.*;
import org.springframework.stereotype.*;

@Service
public class AlbumService {

    public void create(Album album) {

        //omitted code - call to a repository
        System.out.println("Album " + album + " was created in the database ");

    }

    public void delete(Album album) {

        //omitted code - call to a repository
        System.out.println("Album " + album + " was deleted from the database.");

    }
}
