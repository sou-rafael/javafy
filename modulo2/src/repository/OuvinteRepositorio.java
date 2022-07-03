package repository;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import views.Menus;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static views.Menus.formatter;

public class OuvinteRepositorio implements Repositorio<Integer, Ouvinte>{

    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT seq_id_ouvinte.nextval ouvinteSequence FROM OUVINTE";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            if(result.next()){
                return result.getInt("ouvinteSequence");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Ouvinte adicionar(Ouvinte ouvinte) throws BancoDeDadosException {

        Connection con = null;
        try{

            con = ConexaoBancoDeDados.getConnection();
            Integer idOuvinte = this.getProximoId(con);
            ouvinte.setIdOuvinte(idOuvinte);

            String sql = "INSERT INTO OUVINTE\n" +
                    "(ID_OUVINTE, ID_USER)\n" +
                    "VALUES(?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, ouvinte.getIdOuvinte());
            // pegar o id do usuario que corresponde aquele ouvinte
            stmt.setInt(2, ouvinte.getIdUser());

            int res = stmt.executeUpdate();
            System.out.println("adicionarOuvinte.res=" + res);
            return ouvinte;

        }catch (SQLException ex){
            //colocar nossa exception
            throw new BancoDeDadosException(ex.getCause());
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
    public boolean remover(Integer idOuvinte) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM OUVINTE WHERE ID_OUVINTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idOuvinte);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerOuvintePorId.res=" + res);

            return res > 0;
        } catch (SQLException ex) {
            //colocar nossa exception
            throw new BancoDeDadosException(ex.getCause());
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
    public boolean editar(Integer id, Ouvinte ouvinte) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET \n");

            if (ouvinte != null) {
                if (ouvinte.getIdUser() != null) {
                    sql.append(" id_user = ?,");
                }
                if (ouvinte.getGenero() != null) {
                    sql.append(" genero = ?,");
                }
                if (ouvinte.getPremium() != null) {
                    sql.append(" premium = ?,");
                }
                if (ouvinte.getNome() != null) {
                    sql.append(" nome = ?,");
                }
                if (ouvinte.getDataNascimento() != null) {
                    sql.append(" data_nascimento = to_date( ?, 'DD/MM/YYYY'),");
                }
            }


            sql.deleteCharAt(sql.length() - 1); //remove o ultimo ','
            sql.append(" WHERE id_user = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (ouvinte != null) {
                if (ouvinte.getIdUser() != null) {
                    stmt.setInt(index++, ouvinte.getIdUser());
                }
            }
            if (ouvinte.getGenero() != null) {
                stmt.setString(index++, ouvinte.getGenero());
            }
            if (ouvinte.getNome() != null) {
                stmt.setString(index++, ouvinte.getNome());
            }
            if (ouvinte.getDataNascimento() != null) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(ouvinte.getDataNascimento(), formato);
                stmt.setDate(index++, Date.valueOf(data));
            }
            if (ouvinte.getPremium() != null) {
                stmt.setInt(index++, ouvinte.getPremium());
            }

            stmt.setInt(index++, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarOuvinte.res=" + res);

            return res > 0;
        } catch (SQLException ex) {
            throw new BancoDeDadosException(ex.getCause());
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
    public List<Ouvinte> listar() throws BancoDeDadosException {
        List<Ouvinte> ouvintes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT *, " +
                    "            o.NOME AS NOME_PESSOA " +
                    "  FROM VEM_SER.OUVINTE o " +
                    "  LEFT JOIN USUARIO u ON (o.ID_USUARIO = u.ID_USUARIO) ";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Ouvinte ouvinte = getOuvinteFromResultSet(res);
                ouvintes.add(ouvinte);
            }
            return ouvintes;
        } catch (SQLException e) {
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

    // Só para entender o processo
    public Ouvinte getOuvinte(Integer id){
        Connection con = null;
        try {
            Ouvinte ouvinte = new Ouvinte();
            List<Playlist> playlists = new ArrayList<>();
            List<Musica> musicas = new ArrayList<>();

            String sqlOuvinte = "SELECT * FROM VEM_SER.OUVINTE o \n" +
                    "JOIN USUARIO u ON u.ID_USER = o.ID_USER \n" +
                    "WHERE o.ID_OUVINTE  = " + id;

            String sqlPlayListUser = "SELECT * FROM PLAYLIST p  \n" +
                    "WHERE p.ID_OUVINTE  = " + id;

            String sqlMusicas = "SELECT * FROM LISTADEMUSICAS l  \n" +
                    "JOIN MUSICA m ON m.ID_MUSICA  = l.ID_MUSICA \n" +
                    "WHERE l.ID_PLAYLIST  = ";

            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlOuvinte);

            ResultSet oSet = stmt.executeQuery();

            while (oSet.next()) {
                ouvinte.setIdUser(oSet.getInt("id_user"));
                ouvinte.setIdOuvinte(oSet.getInt("id_ouvinte"));
                ouvinte.setNome(oSet.getString("nome"));
                ouvinte.setDataNascimento(oSet.getString("data_nascimento"));
                ouvinte.setGenero(oSet.getString("genero"));
                ouvinte.setPremium(oSet.getInt("premium"));
            }

            stmt = con.prepareStatement(sqlPlayListUser);
            ResultSet playSet = stmt.executeQuery();

            while (playSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setProprietario(ouvinte);
                playlist.setNome(playSet.getString("nome"));
                playlist.setIdPlaylist(playSet.getInt("id_playlist"));

                sqlMusicas += playlist.getIdPlaylist();
                stmt = con.prepareStatement(sqlMusicas);
                ResultSet musicSet = stmt.executeQuery();
                System.out.println("NOME DA PLAYLIST: " + playlist.getNome());
                while (musicSet.next()) {
                    System.out.println("IMPRIMINDO MUSICAS");
                    System.out.println("NOME: " + musicSet.getString("nome"));
                }
            }
            System.out.println(ouvinte);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private Ouvinte getOuvinteFromResultSet(ResultSet res) throws SQLException {
        Ouvinte ouvinte = new Ouvinte();
        ouvinte.setIdOuvinte(res.getInt("id_ouvinte"));
        ouvinte.setIdUser(res.getInt("id_user"));
        ouvinte.setNome(res.getString("nome_ouvinte"));
        ouvinte.setGenero(res.getString("genero_ouvinte"));
        ouvinte.setDataNascimento(String.valueOf(res.getDate("data_nascimento").toLocalDate()));
        ouvinte.setPremium(res.getInt("premium"));
        return ouvinte;
    }

}
