package repository;

import exceptions.BancoDeDadosException;
import models.Playlist;

import java.sql.*;
import java.util.List;

public class PlayListRepository implements Repositorio<Integer, Playlist>{
    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException  {
        try {
            String sql = "SELECT seq_id_playlist.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if(res.next()) {
                return res.getInt("mysequence");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Playlist adicionar(Playlist object) throws BancoDeDadosException {
        Connection con = null;

        try {
            String sql = "INSERT INTO PLAYLIST(ID_PLAYLIST, ID_OUVINTE, NOME)" +
                    "VALUES(?, ?, ?)";

            con = ConexaoBancoDeDados.getConnection();

            Integer promixoId = getProximoId(con);
            object.setIdPlaylist(promixoId);

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, object.getIdPlaylist());
            stmt.setInt(2, object.getProprietario().getIdOuvinte());
            stmt.setString(3, object.getNome());
            int res = stmt.executeUpdate();
            System.out.println("Adicionado playlist " + res);
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean editar(Integer id, Playlist playlist) throws BancoDeDadosException {
        Connection con = null;
        try {
            String sql = "UPDATE VEM_SER.PLAYLIST SET NOME = ? WHERE ID_PLAYLIST = ?";
            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement  stmt = con.prepareStatement(sql);

            stmt.setString(1, playlist.getNome());
            stmt.setInt(2, 1);
            int res = stmt.executeUpdate();
            return  res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if(con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Playlist> listar() {


        return null;
    }
}
