package repository;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Playlist;

import java.sql.*;

public class ListaDeMusicaRepository {

    public boolean adicionar(Playlist playlist, Musica musica) throws BancoDeDadosException {
        Connection con = null;
        try {
            String sql = "INSERT INTO LISTADEMUSICAS(ID_PLAYLIST, ID_MUSICA) VALUES (?, ?)";

            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, playlist.getIdPlaylist());
            stmt.setInt(2, musica.getIdMusica());

            System.out.println("Musica adicionada com sucesso!!! ");
            return stmt.executeUpdate() > 0;

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



    };

    public boolean getMusicaInPlayList(Integer idPlayList, Integer idMusica) throws BancoDeDadosException {
        String sql = "SELECT * FROM LISTADEMUSICAS p\n" +
                "WHERE p.ID_PLAYLIST = " + idPlayList + " AND p.id_musica = " + idMusica;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql).isBeforeFirst();
        }catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }

    }



}
