package repository;

import abstracts.Usuario;
import models.Ouvinte;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OuvinteRepositorio implements Repositorio<Integer, Ouvinte>{

    @Override
    public Integer getProximoId(Connection connection) {

        try{
            String sql = "SELECT seq_id_ouvinte.nextval ouvinteSequence FROM OUVINTE";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            if(result.next()){
                return result.getInt("ouvinteSequence");
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }

    }

    @Override
    public Ouvinte adicionar(Ouvinte ouvinte) {

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
    public boolean remover(Integer idOuvinte) {
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
    public boolean editar(Integer id, Ouvinte ouvinte) {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ouvinte SET \n");
            Usuario usuario = ouvinte.getUsuario();
            if (usuario != null) {
                if (usuario.getIdUser() != null) {
                    sql.append(" id_usuario = ?,");
                }
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
                sql.append(" dataNascimento = ?,");
            }

            sql.deleteCharAt(sql.length() - 1); //remove o ultimo ','
            sql.append(" WHERE id_ouvinte = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (usuario != null) {
                if (usuario.getIdUser() != null) {
                    stmt.setInt(index++, usuario.getIdUser());
                }
            }
            if (ouvinte.getGenero() != null) {
                assert usuario != null;
                stmt.setString(index++, usuario.getGenero());
            }
            if (ouvinte.getNome() != null) {
                stmt.setString(index++, ouvinte.getNome());
            }
            if (ouvinte.getDataNascimento() != null) {
                stmt.setString(index++, ouvinte.getDataNascimento());
            }
            if (ouvinte.getPremium() != null) {
                stmt.setString(index++, ouvinte.getPremium());
            }

            stmt.setInt(index++, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarOuvinte.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public List<Ouvinte> listar() {
        List<Ouvinte> ouvintes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT *, " +
                    "            o.NOME AS NOME_PESSOA " +
                    "       FROM OUVINTE o " +
                    "  LEFT JOIN USUARIO u ON (o.ID_USUARIO = u.ID_USUARIO) ";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                // adcionar esse metodo a classe usuario repository
                //Ouvinte ouvinte = getOuvinteFromResultSet(res);
                //ouvintes.add(ouvinte);
            }
            return ouvintes;
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
