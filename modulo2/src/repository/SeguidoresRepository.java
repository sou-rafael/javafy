package repository;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Ouvinte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguidoresRepository {

    public List<Usuario> getAllSeguidores(Integer idUsuario) throws BancoDeDadosException{

        Connection con = null;
        String sql = "SELECT u.ID_USER, u.NOME, u.DATA_NASCIMENTO , u.GENERO, u.PREMIUM  " +
                    "FROM SEGUIDORES s " +
                    "JOIN USUARIO u ON u.ID_USER = s.ID_USER " +
                    "WHERE s.ID_USER_SEGUINDO  = " + idUsuario;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Vamos só listar os usuários - Então podemos criar um Ouvinte sem ID
            while (resultSet.next()){
                Usuario usuario = new Ouvinte();
                usuario.setIdUser(resultSet.getInt("ID_USER"));
                usuario.setNome(resultSet.getString("NOME"));
                usuario.setDataNascimento(resultSet.getString("DATA_NASCIMENTO"));
                usuario.setGenero(resultSet.getString("GENERO"));
                usuario.setPremium(resultSet.getInt("PREMIUM"));
                usuarios.add(usuario);
            }

            return usuarios;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }

    }

    public List<Usuario> getAllSeguindo(Integer idUsuario) throws BancoDeDadosException{
        Connection con = null;
        String sql = "SELECT u.ID_USER, u.NOME, u.DATA_NASCIMENTO, u.GENERO, u.PREMIUM " +
                " FROM VEM_SER.SEGUIDORES s " +
                "JOIN USUARIO u ON u.ID_USER = s.ID_USER_SEGUINDO " +
                "WHERE s.ID_USER = " + idUsuario;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Vamos só listar os usuários - Então podemos criar um Ouvinte sem ID
            while (resultSet.next()){
                Usuario usuario = new Ouvinte();
                usuario.setIdUser(resultSet.getInt("ID_USER"));
                usuario.setNome(resultSet.getString("NOME"));
                usuario.setDataNascimento(resultSet.getString("DATA_NASCIMENTO"));
                usuario.setGenero(resultSet.getString("GENERO"));
                usuario.setPremium(resultSet.getInt("PREMIUM"));
                usuarios.add(usuario);
            }

            return usuarios;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }

    }

    public boolean seguirUsuario(Integer idUsuarioParaSeguir, Usuario usuario) throws BancoDeDadosException{
        Connection con = null;
        String sql = "INSERT INTO SEGUIDORES (ID_USER, ID_USER_SEGUINDO) VALUES (?,?)";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getIdUser());
            stmt.setInt(2, idUsuarioParaSeguir);

            int res = stmt.executeUpdate();
            System.out.println("RES " + res);
            return res > 0;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public boolean deixarDeSeguirUsuario(Integer idUsuarioSeguindo, Usuario usuario)
            throws BancoDeDadosException{
        Connection con = null;
        String sql = "DELETE FROM SEGUIDORES s WHERE s.ID_USER = ? AND s.ID_USER_SEGUINDO = ?";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = ConexaoBancoDeDados.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getIdUser());
            stmt.setInt(2, idUsuarioSeguindo);

            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

}
