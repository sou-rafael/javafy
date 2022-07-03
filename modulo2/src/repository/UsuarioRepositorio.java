package repository;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Ouvinte;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioRepositorio {

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

    public Ouvinte criarUsuario (Ouvinte ouvinte) throws BancoDeDadosException {
        Connection con = null;
        try{

            con = ConexaoBancoDeDados.getConnection();
            Integer idUsuario = this.getProximoId(con);
            System.out.println(idUsuario);
            ouvinte.setIdUser(idUsuario);

            String sql = "INSERT INTO USUARIO\n" +
                    "(ID_USER, NOME, DATA_NASCIMENTO, GENERO, PREMIUM)\n" +
                    "VALUES(?, ?, TO_DATE( ?, 'DD/MM/YYYY' ), ?, ? )\n";

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setInt(1, ouvinte.getIdUser());
            stmt.setString(2, ouvinte.getNome());
            stmt.setString(3, ouvinte.getDataNascimento());
            stmt.setString(4, ouvinte.getGenero());
            stmt.setInt(5, ouvinte.getPremium());
            int res = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res=" + res);
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
            System.out.println("editarUsuario.res=" + res);

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
}
