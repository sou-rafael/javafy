package models;

import abstracts.PlayListModel;

public class Album extends PlayListModel<Artista> {
    private String biografia;
    private Integer avaliacao;

    public Album() {}

    public Album(Integer id_playlist, String nome, Artista proprietario,
                 String biografia, Integer avaliacao) {
        super(id_playlist, nome, proprietario);
        this.biografia = biografia;
        this.avaliacao = avaliacao;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }
}
