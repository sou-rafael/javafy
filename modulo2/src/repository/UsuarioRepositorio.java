package repository;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Ouvinte;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio implements Repositorio<Integer, Ouvinte>{

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT seq_id_user.nextval usuarioSequence FROM USUARIO";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            if(result.next()){
                return result.getInt("usuarioSequence");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public Ouvinte adicionar (Ouvinte ouvinte) throws BancoDeDadosException {
        Connection con = null;
        try{
            con = ConexaoBancoDeDados.getConnection();
            Integer idUsuario = this.getProximoId(con);
            ouvinte.setIdUser(idUsuario);

            String sql = "INSERT INTO USUARIO\n" +
                    "(ID_USER, NOME, DATA_NASCIMENTO, GENERO, PREMIUM)\n" +
                    "VALUES(?, ?, ? , ?, ? )";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, ouvinte.getIdUser());
            stmt.setString(2, ouvinte.getNome());
            stmt.setDate(3, Date.valueOf(ouvinte.getDataNascimento()));
            stmt.setString(4, ouvinte.getGenero());
            stmt.setInt(5, ouvinte.getPremium());
            int res = stmt.executeUpdate();
            //System.out.println("adicionarUsuario.res=" + res);
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

    public boolean editar(Integer id, Ouvinte ouvinte) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET \n");

            sql.append(" nome = ?,");
            sql.append(" genero = ?,");
            sql.append(" data_nascimento = ?,");
            sql.append(" premium = ? ");
            sql.append(" WHERE id_user = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, ouvinte.getNome());
            stmt.setString(2, ouvinte.getGenero());
            stmt.setDate(3, Date.valueOf(ouvinte.getDataNascimento()));
            stmt.setInt(4,ouvinte.getPremium());
            stmt.setInt(5, ouvinte.getIdOuvinte());

            int res = stmt.executeUpdate();
            //System.out.println("editarUsuario.res=" + res);
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

    public List<Usuario> getAllUsers(Ouvinte ouvinte) throws BancoDeDadosException{
        Connection con = null;
        try{
            List<Usuario> usuarios = new ArrayList<>();

            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM USUARIO u WHERE not ID_USER = " + ouvinte.getIdUser();

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Usuario usuario = new Ouvinte();
                usuario.setIdUser(resultSet.getInt("id_user"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setGenero(resultSet.getString("genero"));
                usuarios.add(usuario);
            }
            return usuarios;

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
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM OUVINTE WHERE ID_OUVINTE = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerUsuarioPorId.res=" + res);

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
    public List<Ouvinte> listar() throws BancoDeDadosException {
        List<Ouvinte> ouvintes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT  u.NOME AS NOME_PESSOA" +
                    "            ,u.DATA_NASCIMENTO" +
                    "            ,u.PREMIUM " +
                    "  FROM VEM_SER.USUARIO u " +
                    "  LEFT JOIN OUVINTE o ON (o.ID_USUARIO = u.ID_USUARIO) ";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Ouvinte ouvinte = getUsuarioFromResultSet(res);
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

    private Ouvinte getUsuarioFromResultSet(ResultSet res) throws SQLException {
        Ouvinte ouvinte = new Ouvinte();
        ouvinte.setIdOuvinte(res.getInt("id_ouvinte"));
        ouvinte.setIdUser(res.getInt("id_user"));
        ouvinte.setNome(res.getString("nome"));
        ouvinte.setGenero(res.getString("genero"));
        ouvinte.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
        ouvinte.setPremium(res.getInt("premium"));
        return ouvinte;
    }
}
