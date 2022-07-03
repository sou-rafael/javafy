package repository;

import exceptions.BancoDeDadosException;
import models.Album;
import models.Musica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository implements Repositorio<Integer, Album>{

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT seq_id_album.nextval albumSequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            if(result.next()) {
                return result.getInt("albumSequence");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Album adicionar(Album object) throws SQLException {
        Connection con = null;
        try {
            String sql = "INSERT INTO ALBUM(ID_ALBUM, ID_ARTISTA, NOME, AVALIACAO)" +
                    "VALUES(?, ?, ?, ?)";

            con = ConexaoBancoDeDados.getConnection();

            Integer promixoId = getProximoId(con);
            object.setIdAlbum(promixoId);

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setInt(1, object.getIdAlbum());
            stmt.setInt(2, object.getProprietario().getIdArtista());
            stmt.setString(3, object.getNome());
            stmt.setInt(4,object.getAvaliacao()); // fazer como se fosse um parseString
            int res = stmt.executeUpdate();
            System.out.println("Adicionado o Album: " + res);
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
    public boolean remover(Integer idAlbumDel) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ALBUM WHERE ID_ALBUM = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idAlbumDel);

            int res = stmt.executeUpdate();
            System.out.println("removerAlbumPorId.res=" + res);

            return res > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getCause());
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

    @Override
    public boolean editar(Integer id, Album album) throws BancoDeDadosException {
        Connection con = null;
        try {
            String sql = "UPDATE VEM_SER.ALBUM " +
                    "SET NOME = ? " +
                    "SET AVALIACAO = ?" +
                    "WHERE ID_ALBUM = ?";

            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement  stmt = con.prepareStatement(sql);

            stmt.setString(1, album.getNome());
            stmt.setInt(2, album.getAvaliacao());
            stmt.setInt(3, id);

            System.out.println(stmt.toString());

            //consulta
            int res = stmt.executeUpdate();
            System.out.println("editarAlbum.res=" + res);
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
    public List<Album> listar() throws BancoDeDadosException {
        //vai listar todos os albuns que o ouvinte possui
        List<Album> albuns = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT *\n" +
                    "FROM ALBUM A\n" +
                    "JOIN MUSICA M\n" +
                    "ON (A.ID_ALBUM = M.ID_ALBUM) AND  (ID_ARTISTA = 1)";
// REMOVER O ID_ARTISTA FIXO... ANALISAR SE EH MELHOR DEIXAR P SER ESCOLHIDO

                    /*"SELECT a.ID_ALBUM, a.NOME, a.AVALIACAO "+
                    "FROM ALBUM a " +
                    "LEFT JOIN ARTISTA ar " +
                    "ON (a.ID_ARTISTA = ar.ID_ARTISTA)";*/


            // consulta
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                Album album = new Album();
                List<String> musicas = new ArrayList<>();
                album.setIdAlbum(res.getInt("ID_ALBUM"));
                album.setNome(res.getString("NOME"));
                album.setAvaliacao(res.getInt("AVALIACAO"));
                while(res.next()){
                    musicas.add(res.getString("MUSICA.NOME"));
                }

                //album.setMusicas(musicas);
//CORRIGIR A EXIBICAO DE MUSICA!!!!!!!!!!!!!!!!!!!!!!
                albuns.add(album);
            }
            return albuns;
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
