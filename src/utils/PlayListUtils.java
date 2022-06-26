package utils;

import models.Playlist;

import java.util.ArrayList;
import java.util.Objects;

public class PlayListUtils {

    private Playlist playlist;
    private ArrayList users;

    public PlayListUtils(Playlist playlist, ArrayList users) {
        this.playlist = playlist;
        this.users = users;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public ArrayList getUsers() {
        return users;
    }

    public void setUsers(ArrayList users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayListUtils that = (PlayListUtils) o;
        return Objects.equals(playlist, that.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist);
    }
}
