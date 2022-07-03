package repository;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import models.Playlist;

import java.sql.*;
import java.util.ArrayList;
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
            return object;

        } catch (SQLException e) {
            throw  new BancoDeDadosException(e.getCause());
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
    public boolean remover(Integer id) throws BancoDeDadosException {
        String sql = "DELETE FROM VEM_SER.PLAYLIST p WHERE p.ID_PLAYLIST = " + id;
        System.out.println(sql);
        Connection connection = null;
        List<Playlist> playlists = new ArrayList<>();
        try {
            connection = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if(connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editar(Integer idPlaylist, Playlist playlist) throws BancoDeDadosException {
        Connection con = null;
        try {
            String sql = "UPDATE VEM_SER.PLAYLIST " +
                            "SET NOME = ? " +
                        "WHERE ID_PLAYLIST = ?";

            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement  stmt = con.prepareStatement(sql);

            stmt.setString(1, playlist.getNome());
            stmt.setInt(2, idPlaylist);

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

    public List<Playlist> listar(Ouvinte ouvinte) throws BancoDeDadosException {
        String sql = "SELECT * FROM PLAYLIST p WHERE p.ID_OUVINTE = " + ouvinte.getIdOuvinte();
        Connection connection = null;
        List<Playlist> playlists = new ArrayList<>();
        try {
            connection = ConexaoBancoDeDados.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setProprietario(ouvinte);
                playlist.setIdPlaylist(resultSet.getInt("id_playlist"));
                playlist.setNome(resultSet.getString("nome"));
                playlists.add(playlist);
            }
            return playlists;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if(connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Playlist getPlaylistById(Integer id, Ouvinte ouvinte) throws BancoDeDadosException{
        Connection con = null;
        Playlist playlist = null;
        try {
            String sql = "SELECT * FROM VEM_SER.PLAYLIST P " +
                    "WHERE P.ID_PLAYLIST = " + id + " AND P.ID_OUVINTE = " + ouvinte.getIdOuvinte();

            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                playlist = new Playlist();
                playlist.setIdPlaylist(resultSet.getInt("id_playlist"));
                playlist.setProprietario(ouvinte);
                playlist.setNome(resultSet.getString("nome"));
            }
            return playlist;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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


}
