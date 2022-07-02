package repository;

import exceptions.BancoDeDadosException;
import models.Musica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusicaRepository implements Repositorio<Integer, Musica>{
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try{
            String sql = "SELECT seq_id_musica.nextval musicaSequence FROM DUAL";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            if(result.next()){
                return result.getInt("musicaSequence");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Musica adicionar(Musica object) throws BancoDeDadosException {
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        return false;
    }

    @Override
    public boolean editar(Integer id, Musica musica) throws BancoDeDadosException {
        return false;
    }

    @Override
    public List<Musica> listar() throws BancoDeDadosException {
        Connection con = null;
        try {
            List<Musica> musicas = new ArrayList<>();
            String sql = "SELECT * FROM MUSICA";
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Musica musica = new Musica();
                musica.setIdMusica(resultSet.getInt("id_musica"));
                musica.setNome(resultSet.getString("nome"));
                musica.setAvaliacao(resultSet.getInt("avaliacao"));
                musica.setCurtidas(resultSet.getInt("curtidas"));
                musica.setDuracao(resultSet.getDouble("duracao"));
                musicas.add(musica);
            }
            return musicas;
        } catch (SQLException e){
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Musica getMusica(Integer idMusica) throws BancoDeDadosException{
        Connection con = null;
        Musica musica = new Musica();
        try {
            String sql = "SELECT * FROM MUSICA U WHERE u.id_musica = " + idMusica;
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            // SE o ID NÃO EXISTE O QUERY RETORNARÁ UMA LISTA DE TUPLE VAZIAS
            // ASSIM BASTA VER SE EXISTE UM PONTEIRO NA LISTA DE TUPLE,
            // SIGNIFICA QUE TEM DADOS, LOGO O QUERY FOI FEITO COM SUCESSO
            if(!resultSet.isBeforeFirst()){
                return null;
            }

            while (resultSet.next()) {
                musica.setIdMusica(resultSet.getInt("id_musica"));
                musica.setNome(resultSet.getString("nome"));
                musica.setDuracao(resultSet.getDouble("duracao"));
                musica.setCurtidas(resultSet.getInt("curtidas"));
                musica.setAvaliacao(resultSet.getInt("avaliacao"));
            }
            return musica;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }
}
