package models;

import abstracts.User;

import java.util.ArrayList;

public class Album extends Playlist{

    private Integer avaliacao;

    public Album(String nomePlaylist, String id, User propietario) {
        super(nomePlaylist, id, propietario);
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        if(avaliacao != null && avaliacao >= 0 && avaliacao <= 5){
            this.avaliacao = avaliacao;
        }
    }

}
