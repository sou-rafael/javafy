package repository;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import service.MusicaService;
import service.PlayListService;
import views.Menus;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeMusicaRepository {

    private void fechaOBanco(Connection conn){
        try {
            if(conn !=null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adiciona a playlist e musica no banco de dados
    public boolean adicionar(Playlist playlist, Musica musica) throws BancoDeDadosException {
        Connection con = null;
        try {
            String sql = "INSERT INTO LISTADEMUSICAS(ID_PLAYLIST, ID_MUSICA) " +
                    "VALUES (?, ?)";

            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, playlist.getIdPlaylist());
            stmt.setInt(2, musica.getIdMusica());

            Menus.imprimirBlue("Musica adicionada com sucesso!!! ");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fechaOBanco(con);
        }
    };

    // Retornar true se a musica não tiver na playlist
    public boolean verificaSeMusicaEstaPlayList(Integer idPlayList, Integer idMusica)
            throws BancoDeDadosException {

        String sql = "SELECT * FROM LISTADEMUSICAS p\n" +
                "WHERE p.ID_PLAYLIST = " + idPlayList + " AND p.id_musica = " + idMusica;

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql).isBeforeFirst();
        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fechaOBanco(con);
        }

    }

    // Pega uma lista de musicas que está na playlist
    public List<Musica> getMusicaInPlayList(Playlist playlist) throws BancoDeDadosException {

        String sql = "SELECT l.ID_MUSICA, m.NOME, m.AVALIACAO, m.DURACAO, m.CURTIDAS  FROM LISTADEMUSICAS l " +
                    "JOIN MUSICA m ON m.ID_MUSICA = l.ID_MUSICA " +
            "WHERE l.ID_PLAYLIST = " + playlist.getIdPlaylist();

        Connection con = null;
        List<Musica> musicas = new ArrayList<>();

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()){
                Musica musica = new Musica();
                musica.setIdMusica(resultSet.getInt("id_musica"));
                musica.setNome(resultSet.getString("nome"));
                musica.setAvaliacao(resultSet.getInt("avaliacao"));
                musica.setDuracao(resultSet.getDouble("duracao"));
                musica.setCurtidas(resultSet.getInt("curtidas"));
                musicas.add(musica);
            }
            return musicas;
        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fechaOBanco(con);
        }
    }

    // Remove uma musica da playlist
    public boolean removerMusicaDaPlayList(Integer idMusica, Playlist playlist)
            throws BancoDeDadosException {

        String sql = "DELETE FROM LISTADEMUSICAS l " +
                        "WHERE l.ID_PLAYLIST = " + playlist.getIdPlaylist()
                        + " AND " + " l.ID_MUSICA = " + idMusica ;

        Connection con = null;

        try{
            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            fechaOBanco(con);
        }
    }

}
