package abstracts;

import java.util.ArrayList;
import java.util.Objects;

public abstract class PlayListAbstract<T> {

    private T playlist;
    private ArrayList users = new ArrayList<>();

    public PlayListAbstract(T playlist) {
        this.playlist = playlist;
    }

    public T getPlaylist() {
        return playlist;
    }

    public void setPlaylist(T playlist) {
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
        PlayListAbstract<?> that = (PlayListAbstract<?>) o;
        return Objects.equals(playlist, that.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist);
    }
}
