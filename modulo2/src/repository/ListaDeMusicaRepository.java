package repository;

import models.Musica;
import models.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListaDeMusicaRepository {

    public boolean adicionar(Playlist playlist, Musica musica) throws SQLException {
        String sql = "INSERT INTO LISTADEMUSICAS(ID_PLAYLIST, ID_MUSICA) VALUES (?, ?)";
        Connection conn = ConexaoBancoDeDados.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, playlist.getIdPlaylist());
        stmt.setInt(2, musica.getIdMusica());
        Integer res = stmt.executeUpdate();
        System.out.println("LISTA DE MUSICA " + res);
        return res > 0;
    };



}
