package repository;

import exceptions.BancoDeDadosException;
import models.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaRepository implements Repositorio<Integer, Artista>{
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        return null;
        // nao usar pq os artistas virao da base de dados
        // nao vou poder criar um artista
    }

    @Override
    public Artista adicionar(Artista object) throws SQLException {
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        return false;
    }

    @Override
    public boolean editar(Integer id, Artista artista) throws BancoDeDadosException {
        return false;
    }

    @Override
    public List<Artista> listar() throws BancoDeDadosException {
        List<Artista> artistas = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT ID_ARTISTA, BIO, AVALIACAO  \n" +
                    "FROM ARTISTA";
            // consulta
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Artista artista = new Artista();
                artista.setIdArtista(res.getInt("ID_ARTISTA"));
                artista.setNome(res.getString("BIO"));
                artista.setAvaliacao(res.getInt("AVALIACAO"));
                artistas.add(artista);
            }
            return artistas;
        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
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
}
