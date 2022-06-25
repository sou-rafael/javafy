package abstracts;

import java.util.Objects;

public abstract class Conta {
    private String nome;
    private Integer id;
    private String dataDeNascimento;
    private String genero;

    public Conta(){}

    public Conta(String nome, Integer id, String dataDeNascimento, String genero) {
        this.nome = nome;
        this.id = id;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(nome, conta.nome) && Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, id);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", genero='" + genero +
                '}';
    }
}