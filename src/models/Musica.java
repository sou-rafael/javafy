package models;

public class Musica {

    private String id;
    // Add artista
    private Integer avaliacao;
    private Double duracao;
    private Integer curtidas;

    public Musica(){
        avaliacao = 0;
        duracao = 0.0;
        curtidas = 0;

    }

    public Musica(String id, Integer avaliacao, Double duracao, Integer curtidas) {
        this.id = id;
        this.avaliacao = avaliacao;
        this.duracao = duracao;
        this.curtidas = curtidas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }

    @Override
    public String toString() {
        return "Musica{id='" + id +
                ", avaliacao=" + avaliacao +
                ", duracao=" + duracao +
                ", curtidas=" + curtidas +
                "}";
    }
}
