package utils.bd;

import models.Album;

import java.util.ArrayList;

public class AlbunsArtistasBd {
    ArrayList<Album> albums = new ArrayList<>();

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
