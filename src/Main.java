import models.Ouvinte;
import models.Playlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static Ouvinte getOuvinte() {
        return new Ouvinte("Cleber", "1",
                "13-10-1994", "Ma", true);
    }

    public static Playlist getPlaylist(Ouvinte ouvinte) {
        return new Playlist("Raqs music", "1", ouvinte);
    }
}

