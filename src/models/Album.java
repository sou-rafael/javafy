package models;

import abstracts.User;

import java.util.ArrayList;

public class Album extends Playlist{

    private Double avaliacao;
    private String generoMusical;

    public Album(String nomePlaylist, String id, User propietario, Double avaliacao,
                 String generoMusical) {
        super(nomePlaylist, id, propietario);
        this.avaliacao = avaliacao;
        this.generoMusical = generoMusical;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        if(avaliacao != null && avaliacao >= 0 && avaliacao <= 5){
            this.avaliacao = avaliacao;
        }
    }

    @Override
    public void setListaMusicas(ArrayList<Musica> listaMusicas) {

        if(listaMusicas != null){
            Integer tamanho = listaMusicas.size();
            if(tamanho < 3){
                System.out.println("Opa. A lista deve ter no mínimo 3 musicas.");
            } else{
                this.getListaMusicas().addAll(listaMusicas);
            }
        }
    }

}
